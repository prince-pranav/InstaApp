package com.prince.apps.instaapp.ui.signup

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.lifecycle.Observer
import com.prince.apps.instaapp.R
import com.prince.apps.instaapp.di.component.ActivityComponent
import com.prince.apps.instaapp.ui.base.BaseActivity
import com.prince.apps.instaapp.ui.login.LoginActivity
import com.prince.apps.instaapp.utils.common.Status
import kotlinx.android.synthetic.main.activity_signup.*

class SignUpActivity : BaseActivity<SignUpViewModel>() {

    companion object {
        const val TAG = "Sign Up activity"
    }

    override fun provideLayoutId(): Int = R.layout.activity_signup

    override fun injectDependencies(activityComponent: ActivityComponent) {
        activityComponent.inject(this)
    }

    override fun setupView(savedInstanceState: Bundle?) {

        et_signup_email.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                viewModel.onEmailFieldChange(s.toString())
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })

        et_signup_password.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                viewModel.onPasswordFieldChange(s.toString())
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })

        et_signup_name.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                viewModel.onNameField(s.toString())
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })

        btn_sign_up.setOnClickListener {
            viewModel.onSignUp()
        }

        tv_login.setOnClickListener {
            startActivity(Intent(applicationContext, LoginActivity::class.java))
            finish()
        }
    }

    override fun setupObservers() {
        super.setupObservers()

        viewModel.emailField.observe(this, Observer {
            if (et_signup_email.text.toString() != it)
                et_signup_email.setText(it)
        })

        viewModel.passwordField.observe(this, Observer {
            if (et_signup_password.text.toString() != it)
                et_signup_password.setText(it)
        })

        viewModel.launchLoginActivity.observe(this, Observer {
            it.getIfNotHandled()?.run {
                startActivity(Intent(applicationContext,LoginActivity::class.java))
                finish()
            }
        })

        viewModel.emailValidation.observe(this, Observer {
            when (it.status) {
                Status.ERROR -> layout_signup_email.error = it.data?.run { getString(this) }
                else -> layout_signup_email.isErrorEnabled = false
            }
        })

        viewModel.passwordValidation.observe(this, Observer {
            when (it.status) {
                Status.ERROR -> layout_signup_password.error = it.data?.run { getString(this) }
                else -> layout_signup_password.isErrorEnabled = false
            }
        })

        viewModel.signUpIn.observe(this, Observer {
            pb_sign_up_loading.visibility = if (it) View.VISIBLE else View.GONE
        })
    }
}
