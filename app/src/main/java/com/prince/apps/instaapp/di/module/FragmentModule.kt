package com.prince.apps.instaapp.di.module

import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.prince.apps.instaapp.data.repository.PostRepository
import com.prince.apps.instaapp.data.repository.UserRepository
import com.prince.apps.instaapp.ui.base.BaseFragment
import com.prince.apps.instaapp.ui.home.HomeViewModel
import com.prince.apps.instaapp.ui.home.PhotoViewModel
import com.prince.apps.instaapp.ui.home.ProfileViewModel
import com.prince.apps.instaapp.ui.dummy.DummyViewModel
import com.prince.apps.instaapp.ui.home.posts.PostsAdapter
import com.prince.apps.instaapp.utils.ViewModelProviderFactory
import com.prince.apps.instaapp.utils.network.NetworkHelper
import com.prince.apps.instaapp.utils.rx.SchedulerProvider
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.processors.PublishProcessor

/**
 * Created by prince patel on 7/24/2019.
 */
@Module
class FragmentModule(private val fragment: BaseFragment<*>) {

    @Provides
    fun provideLinearLayoutManager(): LinearLayoutManager = LinearLayoutManager(fragment.context)

    @Provides
    fun provideHomeViewModel(
        schedulerProvider: SchedulerProvider,
        compositeDisposable: CompositeDisposable,
        networkHelper: NetworkHelper,
        userRepository: UserRepository,
        postRepository: PostRepository
    ): HomeViewModel = ViewModelProviders.of(
        fragment, ViewModelProviderFactory(HomeViewModel::class) {
            HomeViewModel(
                schedulerProvider,
                compositeDisposable,
                networkHelper,
                postRepository,
                userRepository,
                ArrayList(),
                PublishProcessor.create()
            )
        }).get(HomeViewModel::class.java)

    @Provides
    fun provideProfileViewModel(
        schedulerProvider: SchedulerProvider,
        compositeDisposable: CompositeDisposable,
        networkHelper: NetworkHelper
    ): ProfileViewModel = ViewModelProviders.of(
        fragment, ViewModelProviderFactory(ProfileViewModel::class) {
            ProfileViewModel(schedulerProvider, compositeDisposable, networkHelper)
        }).get(ProfileViewModel::class.java)

    @Provides
    fun providePhotoViewModel(
        schedulerProvider: SchedulerProvider,
        compositeDisposable: CompositeDisposable,
        networkHelper: NetworkHelper
    ): PhotoViewModel = ViewModelProviders.of(
        fragment, ViewModelProviderFactory(PhotoViewModel::class) {
            PhotoViewModel(schedulerProvider, compositeDisposable, networkHelper)
        }).get(PhotoViewModel::class.java)

    @Provides
    fun providePostsAdapter() = PostsAdapter(fragment.lifecycle, ArrayList())

    @Provides
    fun provideDummyViewModel(
        schedulerProvider: SchedulerProvider,
        compositeDisposable: CompositeDisposable,
        networkHelper: NetworkHelper
    ): DummyViewModel = ViewModelProviders.of(
        fragment, ViewModelProviderFactory(DummyViewModel::class) {
            DummyViewModel(schedulerProvider, compositeDisposable, networkHelper)
        }).get(DummyViewModel::class.java)
}