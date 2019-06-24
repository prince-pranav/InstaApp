package com.prince.apps.instaapp

import android.app.Application
import com.prince.apps.instaapp.di.component.ApplicationComponent
import com.prince.apps.instaapp.di.component.DaggerApplicationComponent
import com.prince.apps.instaapp.di.module.ApplicationModule

/**
 * Created by prince patel on 6/23/2019.
 */
class InstaApp : Application() {

    lateinit var applicationComponent : ApplicationComponent

    override fun onCreate() {
        super.onCreate()
    }

    private fun injectDependencies() {
        applicationComponent = DaggerApplicationComponent
            .builder()
            .applicationModule(ApplicationModule(this))
            .build()
        applicationComponent.inject(this)
    }
}