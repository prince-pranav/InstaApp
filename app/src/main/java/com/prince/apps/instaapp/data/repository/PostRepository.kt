package com.prince.apps.instaapp.data.repository

import com.prince.apps.instaapp.data.NetworkService
import com.prince.apps.instaapp.data.local.db.DatabaseService
import com.prince.apps.instaapp.data.model.Post
import com.prince.apps.instaapp.data.model.User
import com.prince.apps.instaapp.data.remote.request.PostLikeModifyRequest
import io.reactivex.Single
import javax.inject.Inject

/**
 * Created by prince patel on 7/26/2019.
 */
class PostRepository @Inject constructor(
    private val networkService: NetworkService,
    private val databaseService: DatabaseService
) {

    fun fetchPostList(firstPostId: String?, lastPostId: String?, user: User): Single<List<Post>> {
        return networkService.doPostListCall(
            firstPostId,
            lastPostId,
            user.id,
            user.accessToken
        ).map { it.data }
    }

    fun likePost(post: Post, user: User): Single<Post> {
        return networkService.doPostLikeCall(
            PostLikeModifyRequest(post.id), user.id, user.accessToken
        ).map {
            post.likedBy?.apply {
                this.find { postUser -> postUser.id == user.id } ?: this.add(
                    Post.User(
                        user.id,
                        user.name,
                        user.profilePicUrl
                    )
                )
            }

            return@map post
        }
    }

    fun unLikePost(post: Post, user: User): Single<Post> {
        return networkService.doPostUnlikeCall(
            PostLikeModifyRequest(post.id), user.id, user.accessToken
        ).map {
            post.likedBy?.apply {
                this.find { postUser -> postUser.id == user.id }?.let { this.remove(it) }
            }

            return@map post
        }
    }
}