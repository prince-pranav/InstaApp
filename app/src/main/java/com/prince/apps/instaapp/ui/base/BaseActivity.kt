package com.prince.apps.instaapp.ui.base

import android.os.Bundle
import android.os.PersistableBundle
import androidx.annotation.LayoutRes
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.prince.apps.instaapp.InstaApp
import com.prince.apps.instaapp.di.component.ActivityComponent
import com.prince.apps.instaapp.di.component.DaggerActivityComponent
import com.prince.apps.instaapp.di.module.ActivityModule
import com.prince.apps.instaapp.utils.Log.Logger
import com.prince.apps.instaapp.utils.display.Toaster
import javax.inject.Inject

/**
 * Created by prince patel on 6/23/2019.
 */
abstract class BaseActivity<VM : BaseViewModel> : AppCompatActivity() {

    @Inject
    lateinit var viewModel : VM

    companion object {
        const val TAG = "Base activity"
    }

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        injectDependencies(buildActivityComponent())
        super.onCreate(savedInstanceState, persistentState)
        setContentView(provideLayoutId())
        setupObservers()
        setupView(savedInstanceState)
        viewModel.onCreate()
    }

    private fun buildActivityComponent() =
        DaggerActivityComponent
            .builder()
            .applicationComponent((application as InstaApp).applicationComponent)
            .activityModule(ActivityModule(this))
            .build()

    fun showMessage(message: String) = Toaster.show(applicationContext, message)

    fun showMessage(@StringRes resId: Int) = showMessage(getString(resId))

    protected open fun setupObservers() {
        viewModel.messageString.observe(this, Observer {
            it.data?.run { showMessage(this) }
        })

        viewModel.messageStringId.observe(this, Observer {
            it.data?.run { showMessage(this) }
        })
    }


    open fun goBack() = onBackPressed()

    override fun onBackPressed() {
        if (supportFragmentManager.backStackEntryCount > 0)
            supportFragmentManager.popBackStackImmediate()
        else super.onBackPressed()
    }

    @LayoutRes
    protected abstract fun provideLayoutId(): Int

    protected abstract fun injectDependencies(activityComponent: ActivityComponent)

    protected abstract fun setupView(savedInstanceState: Bundle?)
}