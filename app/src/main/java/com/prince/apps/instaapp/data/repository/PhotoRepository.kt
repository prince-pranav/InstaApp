package com.prince.apps.instaapp.data.repository

import com.prince.apps.instaapp.data.NetworkService
import com.prince.apps.instaapp.data.model.User
import io.reactivex.Single
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.File
import javax.inject.Inject

/**
 * Created by prince patel on 7/31/2019.
 */
class PhotoRepository @Inject constructor(private val networkService: NetworkService) {

    fun uploadPhoto(file: File, user: User): Single<String> {
        return MultipartBody.Part.createFormData(
            "image", file.name, RequestBody.create(MediaType.parse("image/*"), file)
        ).run {
            return@run networkService.doUploadImage(this, user.id, user.accessToken)
                .map { it.data.imageUrl }
        }
    }
}