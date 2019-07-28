package com.prince.apps.instaapp.ui.base

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.annotation.StringRes
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LifecycleRegistry
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import com.prince.apps.instaapp.InstaApp
import com.prince.apps.instaapp.di.component.DaggerViewHolderComponent
import com.prince.apps.instaapp.di.component.ViewHolderComponent
import com.prince.apps.instaapp.di.module.ViewHolderModule
import javax.inject.Inject

/**
 * Created by prince patel on 7/27/2019.
 */
abstract class BaseItemViewHolder<T : Any, VM : BaseItemViewModel<T>>(
    @LayoutRes layoutId: Int, parent: ViewGroup
) : RecyclerView.ViewHolder(LayoutInflater.from(parent.context).inflate(layoutId, parent, false)),
    LifecycleOwner {

    init {
        onCreate()
    }

    override fun getLifecycle(): Lifecycle {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    @Inject
    lateinit var viewModel: VM

    @Inject
    lateinit var lifeCycleRegistry: LifecycleRegistry

    open fun bind(data: T) {
        viewModel.updateData(data)
    }

    protected fun onCreate(){
        injectDependencies(buildViewHolderComponent())
        lifeCycleRegistry.markState(Lifecycle.State.INITIALIZED)
        lifeCycleRegistry.markState(Lifecycle.State.CREATED)
        setupObservers()
        setupView(itemView)
    }

    fun onStart(){
        lifeCycleRegistry.markState(Lifecycle.State.STARTED)
        lifeCycleRegistry.markState(Lifecycle.State.RESUMED)
    }

    fun onStop(){
        lifeCycleRegistry.markState(Lifecycle.State.STARTED)
        lifeCycleRegistry.markState(Lifecycle.State.CREATED)
    }

    fun onDestroy(){
        lifeCycleRegistry.markState(Lifecycle.State.DESTROYED)
    }

    private fun buildViewHolderComponent() =
        DaggerViewHolderComponent
            .builder()
            .applicationComponent((itemView.context!!.applicationContext as InstaApp).applicationComponent)
            .viewHolderModule(ViewHolderModule(this))
            .build()

    protected open fun setupObservers() {
        viewModel.messageString.observe(this, Observer {
            it.data?.run { showMessage(this) }
        })

        viewModel.messageStringId.observe(this, Observer {
            it.data?.run { showMessage(this) }
        })
    }

    private fun showMessage(message: String) = Toast.makeText(itemView.context, message, Toast.LENGTH_LONG).show()

    private fun showMessage(@StringRes resId: Int) = showMessage(itemView.context.getString(resId))

    protected abstract fun injectDependencies(viewHolderComponent: ViewHolderComponent)

    protected abstract fun setupView(view: View)
}