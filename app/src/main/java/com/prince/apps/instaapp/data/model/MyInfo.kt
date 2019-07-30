package com.prince.apps.instaapp.data.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * Created by prince patel on 7/28/2019.
 */
data class MyInfo(

    @Expose
    @SerializedName("id")
    val id: String,

    @Expose
    @SerializedName("name")
    val name: String,

    @Expose
    @SerializedName("profilePicUrl")
    val profilePicUrl: String?,

    @Expose
    @SerializedName("tagline")
    val tagline: String

)
