package com.example.goozixtesttask.network

import com.squareup.moshi.Json

//Model classes for Giphy response
//Ignore discarded Meta and Pagination objects

data class GiphyModel (@Json(name = "data") val gifList: List<Data>)

data class Data (
    val id : String,
    val title : String,
    val images : Images
)

data class Images (
    val original : Original
)

data class Original(
    val height : Int,
    val width : Int,
    val size : Int,
    val url: String
)