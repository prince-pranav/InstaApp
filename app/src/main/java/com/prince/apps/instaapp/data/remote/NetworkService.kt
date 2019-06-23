package com.prince.apps.instaapp.data.remote

import com.mindorks.bootcamp.instagram.data.remote.request.DummyRequest
import com.mindorks.bootcamp.instagram.data.remote.response.DummyResponse
import io.reactivex.Single
import retrofit2.http.Body
import retrofit2.http.Header
import javax.inject.Singleton

/**
 * Created by prince patel on 6/23/2019.
 */
@Singleton
interface NetworkService {

    fun doDummyCall(
        @Body request: DummyRequest,
        @Header(Networking.HEADER_API_KEY) apiKey: String = Networking.API_KEY // default value set when Networking create is called
    ): Single<DummyResponse>

/*
 * Example to add other headers
 *
 *  @POST(Endpoints.DUMMY_PROTECTED)
    fun sampleDummyProtectedCall(
        @Body request: DummyRequest,
        @Header(Networking.HEADER_USER_ID) userId: String, // pass using UserRepository
        @Header(Networking.HEADER_ACCESS_TOKEN) accessToken: String, // pass using UserRepository
        @Header(Networking.HEADER_API_KEY) apiKey: String = Networking.API_KEY // default value set when Networking create is called
    ): Single<DummyResponse>
 */
}