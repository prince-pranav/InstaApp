package com.prince.apps.instaapp.utils.common

import com.bumptech.glide.load.model.GlideUrl
import com.bumptech.glide.load.model.LazyHeaders

/**
 * Created by prince patel on 7/28/2019.
 */

object GlideHelper {
    fun getProtectedUrl(url: String, headers: Map<String, String>): GlideUrl {
        val builder = LazyHeaders.Builder()
        for (entry in headers) builder.addHeader(entry.key, entry.value)
        return GlideUrl(url, builder.build())
    }
}
