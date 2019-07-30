package com.prince.apps.instaapp.ui.profile.edit

import android.os.Bundle
import com.prince.apps.instaapp.R
import com.prince.apps.instaapp.di.component.ActivityComponent
import com.prince.apps.instaapp.ui.base.BaseActivity
import kotlinx.android.synthetic.main.activity_edit_profile.*

/**
 * Created by prince patel on 7/30/2019.
 */
class EditProfileActivity : BaseActivity<EditProfileViewModel>() {

    override fun provideLayoutId(): Int = R.layout.activity_edit_profile


    override fun injectDependencies(activityComponent: ActivityComponent) {
        activityComponent.inject(this)
    }

    override fun setupObservers() {
        super.setupObservers()

    }

    override fun setupView(savedInstanceState: Bundle?) {

        etName.setText(viewModel.user.name)
        etEmail.setText(viewModel.user.email)

        ivClose.setOnClickListener {
            finish()
        }

        ivSave.setOnClickListener {
            viewModel.onSaveProfile()
        }

        tvChangePhoto.setOnClickListener{
            viewModel.onPhotoChange()
        }
    }
}