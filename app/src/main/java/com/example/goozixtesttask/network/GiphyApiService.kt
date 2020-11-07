package com.example.goozixtesttask.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

private const val GIPHY_BASE_URL = " https://api.giphy.com/v1/gifs/"
private const val API_KEY = "CTnxGn5EhQMdBVjMiiCfdF2RqISxV5vB"
private const val LIMIT_VALUE = "5"
private const val RATING_VALUE = "g"


private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(GIPHY_BASE_URL)
    .build()

interface GiphyApiService {
    @GET("trending?api_key=$API_KEY&limit=$LIMIT_VALUE&rating=$RATING_VALUE")
    suspend fun getProperties(): GiphyModel
}

object GiphyApi {
    val retrofitService : GiphyApiService by lazy {
        retrofit.create(GiphyApiService::class.java)
    }
}