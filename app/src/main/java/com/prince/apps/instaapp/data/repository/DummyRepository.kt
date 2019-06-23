package com.prince.apps.instaapp.data.repository

import com.mindorks.bootcamp.instagram.data.remote.request.DummyRequest
import com.prince.apps.instaapp.data.local.db.DatabaseService
import com.prince.apps.instaapp.data.model.Dummy
import com.prince.apps.instaapp.data.remote.NetworkService
import io.reactivex.Single
import javax.inject.Inject

/**
 * Created by prince patel on 6/23/2019.
 */
class DummyRepository @Inject constructor(
    private val networkService: NetworkService,
    private val databaseService: DatabaseService
) {

    fun fetchDummy(id: String): Single<List<Dummy>> =
        networkService.doDummyCall(DummyRequest(id)).map { it.data }
}