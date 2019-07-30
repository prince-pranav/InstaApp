package com.prince.apps.instaapp.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.prince.apps.instaapp.R
import com.prince.apps.instaapp.data.model.MyInfo
import com.prince.apps.instaapp.di.component.FragmentComponent
import com.prince.apps.instaapp.ui.base.BaseFragment
import com.prince.apps.instaapp.ui.main.MainActivity
import com.prince.apps.instaapp.ui.profile.edit.EditProfileActivity
import com.prince.apps.instaapp.utils.common.GlideHelper
import kotlinx.android.synthetic.main.fragment_profile.*
import kotlinx.android.synthetic.main.item_view_post.view.*

/**
 * Created by prince patel on 7/24/2019.
 */
class ProfileFragment : BaseFragment<ProfileViewModel>() {

    companion object {
        const val TAG = "HomeFragment"

        fun newInstance(): ProfileFragment {
            val args = Bundle()
            val fragment = ProfileFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun provideLayoutId(): Int = R.layout.fragment_profile

    override fun injectDependencies(fragmentComponent: FragmentComponent) {
        fragmentComponent.inject(this)
    }

    override fun setupObservers() {
        super.setupObservers()

        viewModel.myInfo.observe(this, Observer {
            updateViews(it)
        })

        viewModel.profileImage.observe(this, Observer {
            it?.run {
                val glideRequest = Glide
                    .with(activity?.baseContext!!)
                    .load(GlideHelper.getProtectedUrl(url, headers))
                    .apply(RequestOptions.circleCropTransform())
                    .apply(RequestOptions.placeholderOf(R.drawable.ic_profile_selected))
                glideRequest.into(ivProfilePic)
            }
        })

        viewModel.editProfile.observe(this, Observer {
            it.getIfNotHandled()?.run {
                startActivity(Intent(activity?.applicationContext, EditProfileActivity::class.java))
            }
        })

    }

    private fun updateViews(myInfo: MyInfo) {
        tvPostsCount.text = "5 Posts"
        tvUsername.text = myInfo.name
        tvTagline.text = myInfo.tagline
    }

    override fun setupView(view: View) {
        btnEditProfile.setOnClickListener {
            viewModel.onEditProfile()
        }
    }
}