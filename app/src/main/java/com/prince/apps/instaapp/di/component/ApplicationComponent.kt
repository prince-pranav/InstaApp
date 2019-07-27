package com.prince.apps.instaapp.di.component

import android.app.Application
import android.content.SharedPreferences
import com.prince.apps.instaapp.InstaApp
import com.prince.apps.instaapp.data.local.db.DatabaseService
import com.prince.apps.instaapp.data.NetworkService
import com.prince.apps.instaapp.data.repository.UserRepository
import com.prince.apps.instaapp.di.module.ApplicationModule
import com.prince.apps.instaapp.utils.network.NetworkHelper
import com.prince.apps.instaapp.utils.rx.SchedulerProvider
import dagger.Component
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Singleton

/**
 * Created by prince patel on 6/23/2019.
 */
@Singleton
@Component(modules = [ApplicationModule::class])
interface ApplicationComponent {

    fun inject(app: InstaApp)

    fun provideApplication(): Application

    fun networkService(): NetworkService

    fun databaseService(): DatabaseService

    fun  getSharedPreferences(): SharedPreferences

    fun getNetWorkHelper(): NetworkHelper

    fun getUserRepository(): UserRepository

    fun getSchedulerProvider(): SchedulerProvider

    fun getCompositeDisposable(): CompositeDisposable
}