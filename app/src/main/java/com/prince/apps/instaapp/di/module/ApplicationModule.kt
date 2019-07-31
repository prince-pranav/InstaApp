package com.prince.apps.instaapp.di.module

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import androidx.room.Room
import com.prince.apps.instaapp.BuildConfig
import com.prince.apps.instaapp.InstaApp
import com.prince.apps.instaapp.data.local.db.DatabaseService
import com.prince.apps.instaapp.utils.network.NetworkHelper
import com.prince.apps.instaapp.data.NetworkService
import com.prince.apps.instaapp.data.Networking
import com.prince.apps.instaapp.di.TempDirectory
import com.prince.apps.instaapp.utils.common.FileUtils
import com.prince.apps.instaapp.utils.rx.RxSchedulerProvider
import com.prince.apps.instaapp.utils.rx.SchedulerProvider
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Singleton

/**
 * Created by prince patel on 6/23/2019.
 */
@Module
class ApplicationModule(private val application: InstaApp) {

    @Provides
    @Singleton
    fun provideApplication(): Application = application

    @Provides
    @Singleton
    fun provideContext(): Context = application

    @Provides
    @Singleton
    @TempDirectory
    fun provideTempDirectory() = FileUtils.getDirectory(application,"temp")

    @Provides
    @Singleton
    fun provideSharedPreferences(): SharedPreferences =
        application.getSharedPreferences("insta-app-prefs", Context.MODE_PRIVATE)

    @Provides
    @Singleton
    fun provideDatabaseService(): DatabaseService =
        Room.databaseBuilder(application, DatabaseService::class.java, "insta-app-db").build()

    @Provides
    @Singleton
    fun provideNetworkService(): NetworkService =
        Networking.create(
            BuildConfig.API_KEY,
            BuildConfig.BASE_URL,
            application.cacheDir,
            10 * 1024 * 1024 // 10MB
        )

    @Singleton
    @Provides
    fun provideNetworkHelper(): NetworkHelper = NetworkHelper(application)

    @Provides
    fun provideCompositeDisposable(): CompositeDisposable = CompositeDisposable()

    @Provides
    fun provideSchedulerProvider(): SchedulerProvider = RxSchedulerProvider()

}