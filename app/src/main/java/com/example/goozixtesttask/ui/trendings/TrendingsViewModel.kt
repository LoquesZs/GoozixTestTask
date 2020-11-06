package com.example.goozixtesttask.ui.trendings

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.goozixtesttask.network.GiphyApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

// ViewModel class attached to the TrendingsFragment

class TrendingsViewModel : ViewModel() {

    private val _response = MutableLiveData<String>()

    val response: LiveData<String>
        get() = _response

    init {
        getGiphyTrendingProperties()
    }

    private fun getGiphyTrendingProperties() {
        GiphyApi.retrofitService.getProperties().enqueue(
            object : Callback<String> {
                override fun onResponse(call: Call<String>, response: Response<String>) {
                    _response.value = response.body()
                }

                override fun onFailure(call: Call<String>, t: Throwable) {
                    _response.value = "Failure" + t.message
                }
            }
        )
    }

}