package com.prince.apps.instaapp.di.module

import androidx.lifecycle.LifecycleRegistry
import com.prince.apps.instaapp.di.ViewHolderScope
import com.prince.apps.instaapp.ui.base.BaseItemViewHolder
import dagger.Module
import dagger.Provides

/**
 * Created by prince patel on 7/27/2019.
 */
@Module
class ViewHolderModule(private val viewHolder :BaseItemViewHolder<*,*>) {

    @Provides
    @ViewHolderScope
    fun provideLifeCycleRegistry() : LifecycleRegistry = LifecycleRegistry(viewHolder)
}