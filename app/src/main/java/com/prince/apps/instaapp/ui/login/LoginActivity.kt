package com.prince.apps.instaapp.ui.login

import android.os.Bundle
import android.os.PersistableBundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity;
import com.prince.apps.instaapp.R
import com.prince.apps.instaapp.di.component.ActivityComponent
import com.prince.apps.instaapp.ui.base.BaseActivity

import kotlinx.android.synthetic.main.activity_login.*

/**
 * Created by prince patel on 6/24/2019.
 */
class LoginActivity : BaseActivity<LoginViewModel>() {

    override fun provideLayoutId(): Int {
        return R.layout.activity_login
    }

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        setContentView(R.layout.activity_login)
    }

    override fun injectDependencies(activityComponent: ActivityComponent) {
        activityComponent.inject(this)
    }

    override fun setupView(savedInstanceState: Bundle?) {

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
    }
}
