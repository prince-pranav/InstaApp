package com.prince.apps.instaapp.ui.base

import androidx.lifecycle.MutableLiveData
import com.prince.apps.instaapp.utils.network.NetworkHelper
import com.prince.apps.instaapp.utils.rx.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable

/**
 * Created by prince patel on 7/27/2019.
 */
abstract class BaseItemViewModel<T : Any>(
    schedulerProvider: SchedulerProvider,
    compositeDisposable: CompositeDisposable,
    networkHelper: NetworkHelper
) : BaseViewModel(schedulerProvider, compositeDisposable, networkHelper) {

    val data = MutableLiveData<T>()

    fun updateData(data: T) {
        this.data.postValue(data)
    }

    fun onManualCleared() = onCleared()



}