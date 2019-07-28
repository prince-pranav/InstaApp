package com.prince.apps.instaapp.ui.home

import androidx.lifecycle.MutableLiveData
import com.prince.apps.instaapp.data.model.Post
import com.prince.apps.instaapp.data.model.User
import com.prince.apps.instaapp.data.repository.PostRepository
import com.prince.apps.instaapp.data.repository.UserRepository
import com.prince.apps.instaapp.ui.base.BaseViewModel
import com.prince.apps.instaapp.utils.common.Resource
import com.prince.apps.instaapp.utils.network.NetworkHelper
import com.prince.apps.instaapp.utils.rx.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.processors.PublishProcessor
import io.reactivex.schedulers.Schedulers

/**
 * Created by prince patel on 7/24/2019.
 */
class HomeViewModel(
    schedulerProvider: SchedulerProvider,
    compositeDisposable: CompositeDisposable,
    networkHelper: NetworkHelper,
    private val postRepository: PostRepository,
    private val userRepository: UserRepository,
    private val allPostList: ArrayList<Post>,
    private val paginator: PublishProcessor<Pair<String?, String?>>
) : BaseViewModel(schedulerProvider, compositeDisposable, networkHelper) {

    val loading: MutableLiveData<Boolean> = MutableLiveData()
    val posts: MutableLiveData<Resource<List<Post>>> = MutableLiveData()

    private val user: User = userRepository.getCurrentUser()!!

    init {
        compositeDisposable.add(
            paginator
                .onBackpressureDrop()
                .doOnNext {
                    loading.postValue(true)
                }
                .concatMapSingle { pageIds ->
                    return@concatMapSingle postRepository
                        .fetchPostList(pageIds.first, pageIds.second, user)
                        .subscribeOn(Schedulers.io())
                        .doOnError {
                            handleNetworkError(it)
                        }
                }
                .subscribe(
                    {
                        allPostList.addAll(it)
                        loading.postValue(false)
                        posts.postValue(Resource.success(it))
                    },
                    {
                        handleNetworkError(it)
                    }
                )
        )
    }

    override fun onCreate() {
        loadMorePosts()
    }

    private fun loadMorePosts() {
        val firstPostId = if (allPostList.isNotEmpty()) allPostList[0].id else null
        val lastPostId = if (allPostList.size > 1) allPostList[allPostList.size - 1].id else null
        if (checkInternetConnectionWithMessage()) paginator.onNext(Pair(firstPostId, lastPostId))
    }

    fun onLoadMore() {
        if (loading.value !== null && loading.value == false) loadMorePosts()
    }
}