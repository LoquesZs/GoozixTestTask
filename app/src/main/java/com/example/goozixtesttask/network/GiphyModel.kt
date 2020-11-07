package com.example.goozixtesttask.network

//Model classes for Giphy response
//Ignore discarded Meta and Pagination objects

data class GiphyModel (val data: List<Data>)

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