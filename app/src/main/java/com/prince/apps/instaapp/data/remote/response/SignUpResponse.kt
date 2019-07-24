package com.prince.apps.instaapp.data.remote.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * Created by prince patel on 7/17/2019.
 */
class SignUpResponse(
    @Expose
    @SerializedName("statusCode")
    val statusCode: String,

    @Expose
    @SerializedName("status")
    val status: Int,

    @Expose
    @SerializedName("message")
    val message: String,

    @Expose
    @SerializedName("accessToken")
    val accessToken: String,

    @Expose
    @SerializedName("refreshToken")
    val refreshToken: String,

    @Expose
    @SerializedName("userId")
    val userId: String,

    @Expose
    @SerializedName("userName")
    val userName: String,

    @Expose
    @SerializedName("userEmail")
    val userEmail: String,

    @Expose
    @SerializedName("profilePicUrl")
    val profilePicUrl: String
)