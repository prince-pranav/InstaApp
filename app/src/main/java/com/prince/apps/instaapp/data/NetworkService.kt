package com.prince.apps.instaapp.data

import com.mindorks.bootcamp.instagram.data.remote.request.DummyRequest
import com.mindorks.bootcamp.instagram.data.remote.response.DummyResponse
import com.mindorks.bootcamp.instagram.data.remote.response.GeneralResponse
import com.prince.apps.instaapp.data.remote.request.LoginRequest
import com.prince.apps.instaapp.data.remote.request.PostLikeModifyRequest
import com.prince.apps.instaapp.data.remote.request.SignUpRequest
import com.prince.apps.instaapp.data.remote.response.*
import io.reactivex.Single
import retrofit2.http.*
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

    @GET(UrlEndpoints.POST_LIST)
    fun doPostListCall(
        @Query("firstPostId") firstPostId:String?,
        @Query("lastPostId") lastPostId:String?,
        @Header(Networking.HEADER_USER_ID) userId: String,
        @Header(Networking.HEADER_ACCESS_TOKEN) accessToken: String,
        @Header(Networking.HEADER_API_KEY) apiKey: String = Networking.API_KEY
    ): Single<PostListResponse>

    @PUT(UrlEndpoints.POST_LIKE)
    fun doPostLikeCall(
        @Body postLikeRequest: PostLikeModifyRequest,
        @Header(Networking.HEADER_USER_ID) userId: String,
        @Header(Networking.HEADER_ACCESS_TOKEN) accessToken: String,
        @Header(Networking.HEADER_API_KEY) apiKey: String = Networking.API_KEY
    ): Single<GeneralResponse>

    @PUT(UrlEndpoints.POST_UNLIKE)
    fun doPostUnlikeCall(
        @Body postUnlikeRequest: PostLikeModifyRequest,
        @Header(Networking.HEADER_USER_ID) userId: String,
        @Header(Networking.HEADER_ACCESS_TOKEN) accessToken: String,
        @Header(Networking.HEADER_API_KEY) apiKey: String = Networking.API_KEY
    ): Single<GeneralResponse>


}