package com.prince.apps.instaapp.di

import javax.inject.Qualifier

/**
 * Created by prince patel on 6/23/2019.
 */
@Qualifier
@Retention(AnnotationRetention.SOURCE)
annotation class ApplicationContext

@Qualifier
@Retention(AnnotationRetention.SOURCE)
annotation class ActivityContext

@Qualifier
@Retention(AnnotationRetention.SOURCE)
annotation class TempDirectory
