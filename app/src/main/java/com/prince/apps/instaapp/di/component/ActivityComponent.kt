package com.prince.apps.instaapp.di.component

import com.prince.apps.instaapp.di.ActivityScope
import com.prince.apps.instaapp.di.module.ActivityModule
import com.prince.apps.instaapp.di.module.ApplicationModule
import com.prince.apps.instaapp.ui.login.LoginActivity
import com.prince.apps.instaapp.ui.splash.SplashActivity
import dagger.Component

/**
 * Created by prince patel on 6/24/2019.
 */
@ActivityScope
@Component(dependencies = [ApplicationComponent::class], modules = [ActivityModule::class])
interface ActivityComponent {

    fun inject(splashActivity: SplashActivity)

    fun inject(loginActivity: LoginActivity)
}