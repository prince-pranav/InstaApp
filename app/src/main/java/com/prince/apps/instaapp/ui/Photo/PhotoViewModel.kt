package com.prince.apps.instaapp.ui.home

import androidx.lifecycle.MutableLiveData
import com.prince.apps.instaapp.R
import com.prince.apps.instaapp.data.model.Post
import com.prince.apps.instaapp.data.repository.PhotoRepository
import com.prince.apps.instaapp.data.repository.PostRepository
import com.prince.apps.instaapp.data.repository.UserRepository
import com.prince.apps.instaapp.ui.base.BaseViewModel
import com.prince.apps.instaapp.utils.Log.Logger
import com.prince.apps.instaapp.utils.common.Event
import com.prince.apps.instaapp.utils.common.FileUtils
import com.prince.apps.instaapp.utils.common.Resource
import com.prince.apps.instaapp.utils.network.NetworkHelper
import com.prince.apps.instaapp.utils.rx.SchedulerProvider
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import java.io.File
import java.io.InputStream

/**
 * Created by prince patel on 7/24/2019.
 */
class PhotoViewModel(
    schedulerProvider: SchedulerProvider,
    compositeDisposable: CompositeDisposable,
    networkHelper: NetworkHelper,
    private val userRepository: UserRepository,
    private val postRepository: PostRepository,
    private val photoRepository: PhotoRepository,
    private val directory: File
) : BaseViewModel(schedulerProvider, compositeDisposable, networkHelper) {

    val loading: MutableLiveData<Boolean> = MutableLiveData()
    val post: MutableLiveData<Event<Post>> = MutableLiveData()

    val user = userRepository.getCurrentUser()!!

    override fun onCreate() {

    }

    fun onGalleryImageSelected(inputStream: InputStream) {
        loading.postValue(true)
        compositeDisposable.add(
            Single.fromCallable {
                FileUtils.saveInputStreamToFile(
                    inputStream, directory, "gallery_img_temp", 500
                )
            }
                .subscribeOn(schedulerProvider.io())
                .subscribe(
                    {
                        if (it != null) {
                            FileUtils.getImageSize(it)?.run {
                                uploadPhotoAndCreatePost(it, this)
                            }
                        } else {
                            loading.postValue(false)
                            messageStringId.postValue(Resource.error(R.string.try_again))
                        }
                    },
                    {
                        loading.postValue(false)
                        messageStringId.postValue(Resource.error(R.string.try_again))
                    }
                )
        )
    }

    fun onCameraImageTaken(cameraImageProcessing: () -> String) {
        loading.postValue(true)
        compositeDisposable.add(
            Single.fromCallable { cameraImageProcessing() }
                .subscribeOn(schedulerProvider.io())
                .subscribe(
                    {
                        File(it).apply {
                            FileUtils.getImageSize(this)?.let { size ->
                                uploadPhotoAndCreatePost(this, size)
                            } ?: loading.postValue(false)
                        }
                    },
                    {
                        loading.postValue(false)
                        messageStringId.postValue(Resource.error(R.string.try_again))
                    }
                )
        )
    }

    private fun uploadPhotoAndCreatePost(file: File, size: Pair<Int, Int>) {
        Logger.d("DEBUG", file.path)
        compositeDisposable.add(
            photoRepository.uploadPhoto(file, user)
                .flatMap {
                    postRepository.createPost(it, size.first, size.second, user)
                }.subscribeOn(schedulerProvider.io())
                .subscribe(
                    {
                        loading.postValue(false)
                        post.postValue(Event(it))
                    },
                    {
                        loading.postValue(false)
                        handleNetworkError(it)
                    }
                )
        )
    }
}