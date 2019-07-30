package com.prince.apps.instaapp.data.repository

import com.prince.apps.instaapp.data.NetworkService
import com.prince.apps.instaapp.data.local.db.DatabaseService
import com.prince.apps.instaapp.data.model.MyInfo
import com.prince.apps.instaapp.data.model.User
import io.reactivex.Single
import javax.inject.Inject

/**
 * Created by prince patel on 7/29/2019.
 */
class MyInfoRepository @Inject constructor(
    private val networkService: NetworkService,
    private val databaseService: DatabaseService
) {
    fun getMyInfo(userId: String, userAccessToken: String): Single<MyInfo> =
        networkService.doGetInfo(
            userId,
            userAccessToken
        ).map { it.data }
}