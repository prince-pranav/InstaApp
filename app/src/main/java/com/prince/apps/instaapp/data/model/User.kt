package com.prince.apps.instaapp.data.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * Created by prince patel on 6/23/2019.
 */
data class User(

    @Expose
    @SerializedName("userId")
    val id: String,

    @Expose
    @SerializedName("userName")
    val name: String,

    @Expose
    @SerializedName("userEmail")
    val email: String,

    @Expose
    @SerializedName("accessToken")
    val accessToken: String,

    @Expose
    @SerializedName("profilePicUrl")
    val profilePicUrl: String? = null
)
