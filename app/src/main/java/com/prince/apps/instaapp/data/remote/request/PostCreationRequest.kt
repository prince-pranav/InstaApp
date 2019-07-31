package com.prince.apps.instaapp.data.remote.request

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * Created by prince patel on 7/31/2019.
 */
class PostCreationRequest(
    @Expose
    @SerializedName("imgUrl")
    var imgUrl: String,

    @Expose
    @SerializedName("imgWidth")
    var imgWidth: Int,

    @Expose
    @SerializedName("imgHeight")
    var imgHeight: Int
)