package com.example.goozixtesttask.network

import com.example.goozixtesttask.ui.trendings.TrendingsViewModel
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

private const val GIPHY_BASE_URL = " https://api.giphy.com/v1/gifs/"
private const val API_KEY = "CTnxGn5EhQMdBVjMiiCfdF2RqISxV5vB"
private const val LIMIT_VALUE = "8"
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
    suspend fun getTrendingModel(): GiphyModel

    @GET("search?api_key=$API_KEY&limit=$LIMIT_VALUE&offset=0&rating=$RATING_VALUE&lang=en")
    suspend fun getSearchModel(@Query("q") searchRequest: String): GiphyModel
}

object GiphyApi {
    val retrofitService : GiphyApiService by lazy {
        retrofit.create(GiphyApiService::class.java)
    }
}