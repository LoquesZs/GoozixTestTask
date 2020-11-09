package com.example.goozixtesttask.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

private const val GIPHY_BASE_URL = " https://api.giphy.com/v1/gifs/"
private const val API_KEY = "CTnxGn5EhQMdBVjMiiCfdF2RqISxV5vB"
internal const val DEFAULT_LIMIT = 10
private const val DEFAULT_RATING = "g"
private const val DEFAULT_LANGUAGE = "en"

/**
 * Retrofit service, interface to GiphyAPI
 * **/

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(GIPHY_BASE_URL)
    .build()

interface GiphyApiService {
    @GET("trending?api_key=$API_KEY")
    suspend fun getTrendingModel(
        @Query("limit") limit: Int = DEFAULT_LIMIT,
        @Query("rating") rating: String = DEFAULT_RATING,
        @Query("offset") offset: Int
    ): GiphyModel

    @GET("search?api_key=$API_KEY&lang=en")
    suspend fun getSearchModel(
        @Query("limit") limit: Int = DEFAULT_LIMIT,
        @Query("rating") rating: String = DEFAULT_RATING,
        @Query("offset") offset: Int,
        @Query("q") searchRequest: String,
        @Query("lang") language: String = DEFAULT_LANGUAGE
    ): GiphyModel
}

object GiphyApi {
    val retrofitService : GiphyApiService by lazy {
        retrofit.create(GiphyApiService::class.java)
    }
}