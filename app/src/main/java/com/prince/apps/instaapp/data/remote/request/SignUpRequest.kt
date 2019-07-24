package com.prince.apps.instaapp.data.remote.request

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


/**
 * Created by prince patel on 7/17/2019.
 */
class SignUpRequest(
    @Expose
    @SerializedName("name")
    val name: String,

    @Expose
    @SerializedName("email")
    val email: String,

    @Expose
    @SerializedName("password")
    val password: String
)

