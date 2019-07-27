package com.prince.apps.instaapp.data.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Post(

    @Expose
    @SerializedName("id")
    val id: String,

    @Expose
    @SerializedName("imgUrl")
    val imgUrl: String,

    @Expose
    @SerializedName("imgHeight")
    val imgHeight: Int,

    @Expose
    @SerializedName("imgWidth")
    val imgWidth: Int,

    @Expose
    @SerializedName("user")
    val postCreator: User,

    @Expose
    @SerializedName("likedBy")
    val likedBy: MutableList<User>,

    @Expose
    @SerializedName("createdAt")
    val createdAt: String
) {


    data class User(

        @Expose
        @SerializedName("")
        val id: String,

        @Expose
        @SerializedName("")
        val name: String,

        @Expose
        @SerializedName("")
        val profilePicUrl: String?

    )


}