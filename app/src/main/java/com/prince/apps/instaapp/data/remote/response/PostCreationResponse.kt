package com.prince.apps.instaapp.data.remote.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.util.*

/**
 * Created by prince patel on 7/31/2019.
 */
class PostCreationResponse(

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
    @SerializedName("data")
    val data: PostData
) {
    data class PostData(
        @Expose
        @SerializedName("id")
        val id: String,

        @Expose
        @SerializedName("imgUrl")
        val imageUrl: String,

        @Expose
        @SerializedName("imgWidth")
        val imageWidth: Int?,

        @Expose
        @SerializedName("imgHeight")
        val imageHeight: Int?,

        @Expose
        @SerializedName("createdAt")
        val createdAt: Date
    )
}