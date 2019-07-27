package com.prince.apps.instaapp.data.remote.request

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * Created by prince patel on 7/26/2019.
 */
class PostLikeModifyRequest(

    @Expose
    @SerializedName("postId")
    var postId : String

)