package com.prince.apps.instaapp.ui.home.posts

import android.view.ViewGroup
import androidx.lifecycle.Lifecycle
import com.prince.apps.instaapp.data.model.Post
import com.prince.apps.instaapp.ui.base.BaseAdapter

/**
 * Created by prince patel on 7/28/2019.
 */
class PostsAdapter(
    parentLifecycle: Lifecycle,
    posts : ArrayList<Post>
) : BaseAdapter<Post, PostItemViewHolder>(parentLifecycle, posts) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostItemViewHolder = PostItemViewHolder(parent)
}