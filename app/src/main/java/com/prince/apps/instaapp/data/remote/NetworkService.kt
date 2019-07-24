package com.prince.apps.instaapp.data.remote

import com.mindorks.bootcamp.instagram.data.remote.request.DummyRequest
import com.mindorks.bootcamp.instagram.data.remote.response.DummyResponse
import com.prince.apps.instaapp.data.remote.request.LoginRequest
import com.prince.apps.instaapp.data.remote.request.SignUpRequest
import com.prince.apps.instaapp.data.remote.response.LoginResponse
import com.prince.apps.instaapp.data.remote.response.SignUpResponse
import com.prince.apps.instaapp.di.UrlEndpoints
import io.reactivex.Single
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST
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

    @POST(UrlEndpoints.LOGIN)
    fun doLogin(
        @Body request: LoginRequest,
        @Header(Networking.HEADER_API_KEY) apiKey: String = Networking.API_KEY
    ): Single<LoginResponse>

    @POST(UrlEndpoints.SIGN_UP)
    fun doSignUp(
        @Body signUpRequest: SignUpRequest,
        @Header(Networking.HEADER_API_KEY) apiKey: String = Networking.API_KEY
        ): Single<SignUpResponse>

}