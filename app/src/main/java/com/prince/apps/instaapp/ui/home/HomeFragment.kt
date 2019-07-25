package com.prince.apps.instaapp.ui.home

import android.os.Bundle
import android.view.View
import com.prince.apps.instaapp.R
import com.prince.apps.instaapp.di.component.FragmentComponent
import com.prince.apps.instaapp.ui.base.BaseFragment

/**
 * Created by prince patel on 7/24/2019.
 */
class HomeFragment : BaseFragment<HomeViewModel>() {


    companion object {
        const val TAG = "HomeFragment"

        fun newInstance(): HomeFragment {
            val args = Bundle()
            val fragment = HomeFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun provideLayoutId(): Int = R.layout.fragment_home

    override fun injectDependencies(fragmentComponent: FragmentComponent) {
        fragmentComponent.inject(this)
    }

    override fun setupView(view: View) {

    }

    override fun setupObservers() {
        super.setupObservers()
    }
}