package com.prince.apps.instaapp.ui.main

import android.util.EventLog
import androidx.lifecycle.MutableLiveData
import com.prince.apps.instaapp.data.model.Post
import com.prince.apps.instaapp.ui.base.BaseViewModel
import com.prince.apps.instaapp.utils.common.Event
import com.prince.apps.instaapp.utils.network.NetworkHelper
import com.prince.apps.instaapp.utils.rx.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable

/**
 * Created by prince patel on 7/31/2019.
 */
class MainSharedViewModel(
    schedulerProvider: SchedulerProvider,
    compositeDisposable: CompositeDisposable,
    networkHelper: NetworkHelper
) : BaseViewModel(schedulerProvider, compositeDisposable, networkHelper) {


    override fun onCreate() {}

    val homeRedirection = MutableLiveData<Event<Boolean>>()

    val newPost: MutableLiveData<Event<Post>> = MutableLiveData()

    fun onHomeRedirect() {
        homeRedirection.postValue(Event(true))
    }

}