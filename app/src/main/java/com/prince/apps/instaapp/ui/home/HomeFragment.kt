package com.prince.apps.instaapp.ui.home

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.prince.apps.instaapp.R
import com.prince.apps.instaapp.di.component.FragmentComponent
import com.prince.apps.instaapp.ui.base.BaseFragment
import com.prince.apps.instaapp.ui.home.posts.PostsAdapter
import kotlinx.android.synthetic.main.fragment_home.*
import javax.inject.Inject

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

    @Inject
    lateinit var linearLayoutManager: LinearLayoutManager

    @Inject
    lateinit var postAdapter: PostsAdapter

    override fun provideLayoutId(): Int = R.layout.fragment_home

    override fun injectDependencies(fragmentComponent: FragmentComponent) {
        fragmentComponent.inject(this)
    }

    override fun setupObservers() {
        super.setupObservers()

        viewModel.posts.observe(this, Observer {
            it.data?.run { postAdapter.appendData(this) }
        })

        viewModel.loading.observe(this, Observer {
            pbPostLoading.visibility = if (it) View.VISIBLE else View.GONE

        })
    }

    override fun setupView(view: View) {
        rvPosts.apply {
            layoutManager = linearLayoutManager
            adapter = postAdapter
            addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    super.onScrolled(recyclerView, dx, dy)
                    layoutManager?.run {
                        if (this is LinearLayoutManager
                            && itemCount > 0
                            && itemCount == findLastVisibleItemPosition() + 1
                        ) viewModel.onLoadMore()
                    }
                }
            })
        }
    }

}