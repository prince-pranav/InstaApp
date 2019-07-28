package com.prince.apps.instaapp.data.model

/**
 * Created by prince patel on 7/28/2019.
 */
data class Image(
    val url: String,
    val headers: Map<String, String>,
    val placeholderWidth: Int = -1,
    val placeholderHeight: Int = -1
)