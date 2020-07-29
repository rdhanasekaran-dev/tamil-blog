package com.dogood.tamilblogs.repository

import com.dogood.tamilblogs.data.DicoverMovie
import com.dogood.tamilblogs.data.RequestTokenEntity
import com.dogood.tamilblogs.data.SessionIdEntity
import com.dogood.tamilblogs.data.UserDetails
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.*
import java.util.*
import kotlin.reflect.jvm.internal.impl.load.kotlin.JvmType

private const val BaseURL="https://api.themoviedb.org/3/"
private val moshi=Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
private val retrofit=Retrofit.Builder().addConverterFactory(MoshiConverterFactory.create(moshi))
                                .addCallAdapterFactory(CoroutineCallAdapterFactory()).baseUrl(BaseURL).build()

interface AppApiService{

    @GET("authentication/token/new")
    fun getToken(
        @Query("api_key") api_key:String
    ):Deferred<RequestTokenEntity>

    @FormUrlEncoded
    @POST("authentication/token/validate_with_login")
    fun createSessionWithLogin(
        @Field("username") username:String,
        @Field("password") password:String,
        @Field("request_token") request_token:String,
        @Query("api_key") api_key:String
    ):Deferred<RequestTokenEntity>

    @FormUrlEncoded
    @POST("authentication/session/new")
    fun createSessionId(
        @Field("request_token") request_token:String,
        @Query("api_key") api_key:String
    ):Deferred<SessionIdEntity>

    @GET("account")
    fun getDetails(
        @Query("api_key") api_key:String,
        @Query("session_id") session_id:String
    ):Deferred<UserDetails>

    @GET("trending/all/day")
    fun discoverMovie(
        @Query("api_key") api_key:String
    ):Deferred<List<DicoverMovie>>

    object AppApi{
        val retrofitService:AppApiService by lazy {
            retrofit.create(AppApiService::class.java)
        }
    }

}