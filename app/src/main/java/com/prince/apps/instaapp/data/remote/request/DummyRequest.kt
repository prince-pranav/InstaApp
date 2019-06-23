package com.mindorks.bootcamp.instagram.data.remote.request

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * Created by prince patel on 6/23/2019.
 */
data class DummyRequest(
    @Expose
    @SerializedName("id")
    var id: String
)