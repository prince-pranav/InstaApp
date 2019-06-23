package com.prince.apps.instaapp.utils.rx

import io.reactivex.Scheduler
import javax.inject.Singleton

/**
 * Created by prince patel on 6/23/2019.
 */
@Singleton
interface SchedulerProvider {

    fun computation(): Scheduler

    fun io(): Scheduler

    fun ui(): Scheduler
}
