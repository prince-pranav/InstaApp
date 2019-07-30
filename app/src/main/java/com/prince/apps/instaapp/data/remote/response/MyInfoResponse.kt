package com.prince.apps.instaapp.data.remote.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.prince.apps.instaapp.data.model.MyInfo

/**
 * Created by prince patel on 7/29/2019.
 */
data class MyInfoResponse(

    @Expose
    @SerializedName("statusCode")
    var statusCode: String,

    @Expose
    @SerializedName("status")
    var status: String,

    @Expose
    @SerializedName("message")
    var message: String,

    @Expose
    @SerializedName("data")
    val data: MyInfo

)