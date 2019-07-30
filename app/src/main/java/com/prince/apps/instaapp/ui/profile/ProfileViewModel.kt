package com.prince.apps.instaapp.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.prince.apps.instaapp.data.Networking
import com.prince.apps.instaapp.data.model.Image
import com.prince.apps.instaapp.data.model.MyInfo
import com.prince.apps.instaapp.data.repository.MyInfoRepository
import com.prince.apps.instaapp.data.repository.UserRepository
import com.prince.apps.instaapp.ui.base.BaseViewModel
import com.prince.apps.instaapp.utils.common.Event
import com.prince.apps.instaapp.utils.network.NetworkHelper
import com.prince.apps.instaapp.utils.rx.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable

/**
 * Created by prince patel on 7/24/2019.
 */
class ProfileViewModel(
    schedulerProvider: SchedulerProvider,
    compositeDisposable: CompositeDisposable,
    networkHelper: NetworkHelper,
    userRepository: UserRepository,
    myInfoRepository: MyInfoRepository
) : BaseViewModel(schedulerProvider, compositeDisposable, networkHelper) {

    companion object {
        const val TAG = "ProfileViewModel"
    }

    private val user = userRepository.getCurrentUser()!!
    private val headers = mapOf(
        Pair(Networking.HEADER_API_KEY, Networking.API_KEY),
        Pair(Networking.HEADER_USER_ID, user.id),
        Pair(Networking.HEADER_ACCESS_TOKEN, user.accessToken)
    )

    val myInfo: MutableLiveData<MyInfo> = MutableLiveData()
    val profileImage: LiveData<Image> = Transformations.map(myInfo) {
        it.profilePicUrl?.run { Image(this, headers) }
    }

    val editProfile: MutableLiveData<Event<Boolean>> = MutableLiveData()

    init {
        compositeDisposable.addAll(
            myInfoRepository.getMyInfo(user.id, user.accessToken)
                .subscribeOn(schedulerProvider.io())
                .subscribe(
                    {
                        myInfo.postValue(it)
                    },
                    {
                        handleNetworkError(it)
                    }
                )
        )
    }

    fun onEditProfile() {
        editProfile.postValue(Event(true))
    }

    override fun onCreate() {

    }
}