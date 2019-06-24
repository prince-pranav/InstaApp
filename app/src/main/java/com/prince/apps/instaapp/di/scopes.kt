package com.prince.apps.instaapp.di

import javax.inject.Scope

/**
 * Created by prince patel on 6/23/2019.
 */
@Scope
@Retention(AnnotationRetention.SOURCE)
annotation class ActivityScope


@Scope
@Retention(AnnotationRetention.SOURCE)
annotation class FragmentScope

@Scope
@Retention(AnnotationRetention.SOURCE)
annotation class ViewModelScope