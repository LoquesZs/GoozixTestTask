package com.example.goozixtesttask.network

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.GET

private const val GIPHY_BASE_URL = " https://api.giphy.com/v1/gifs/"
private const val API_KEY = "CTnxGn5EhQMdBVjMiiCfdF2RqISxV5vB"
private const val LIMIT_VALUE = "5"
private const val RATING_VALUE = "g"

private val retrofit = Retrofit.Builder()
    .addConverterFactory(ScalarsConverterFactory.create())
    .baseUrl(GIPHY_BASE_URL)
    .build()

interface GiphyApiService {
    @GET("trending?api_key=$API_KEY&limit=$LIMIT_VALUE&rating=$RATING_VALUE")
    fun getProperties():
            Call<String>
}

object GiphyApi {
    val retrofitService : GiphyApiService by lazy {
        retrofit.create(GiphyApiService::class.java)
    }
}