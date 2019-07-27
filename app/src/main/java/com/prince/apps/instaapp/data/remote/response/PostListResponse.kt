package com.prince.apps.instaapp.data.remote.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.prince.apps.instaapp.data.model.Post

/**
 * Created by prince patel on 7/26/2019.
 */
class PostListResponse(

    @Expose
    @SerializedName("statusCode")
    var statusCode: String,

    @Expose
    @SerializedName("message")
    var message: String,

    @Expose
    @SerializedName("data")
    val data: List<Post>
)