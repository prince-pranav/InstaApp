package com.prince.apps.instaapp.ui.dummy

import android.view.View
import com.prince.apps.instaapp.R
import com.prince.apps.instaapp.di.component.FragmentComponent
import com.prince.apps.instaapp.ui.base.BaseFragment

/**
 * Created by prince patel on 7/27/2019.
 */
class DummyFragment : BaseFragment<DummyViewModel>() {
    override fun provideLayoutId(): Int = R.layout.dummy_fragment

    override fun injectDependencies(fragmentComponent: FragmentComponent) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun setupView(view: View) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}