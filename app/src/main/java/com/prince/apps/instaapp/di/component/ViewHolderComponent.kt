package com.prince.apps.instaapp.di.component

import androidx.recyclerview.widget.RecyclerView
import com.prince.apps.instaapp.di.ViewHolderScope
import com.prince.apps.instaapp.di.module.ViewHolderModule
import dagger.Component

/**
 * Created by prince patel on 7/27/2019.
 */
@ViewHolderScope
@Component(
    dependencies = [ApplicationComponent::class], modules = [ViewHolderModule::class]
)
interface ViewHolderComponent {

}