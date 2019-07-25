package com.prince.apps.instaapp.di.component

import com.prince.apps.instaapp.di.ActivityScope
import com.prince.apps.instaapp.di.FragmentScope
import com.prince.apps.instaapp.di.module.FragmentModule
import com.prince.apps.instaapp.ui.home.HomeFragment
import com.prince.apps.instaapp.ui.home.PhotoFragment
import com.prince.apps.instaapp.ui.home.ProfileFragment
import dagger.Component

/**
 * Created by prince patel on 7/24/2019.
 */
@FragmentScope
@Component(
    dependencies = [ApplicationComponent::class],
    modules = [FragmentModule::class]
)
interface FragmentComponent {

    fun inject(fragment: HomeFragment)

    fun inject(fragment: PhotoFragment)

    fun inject(fragment: ProfileFragment)
}