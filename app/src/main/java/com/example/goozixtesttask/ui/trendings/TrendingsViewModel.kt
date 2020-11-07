package com.example.goozixtesttask.ui.trendings

import androidx.lifecycle.*
import com.example.goozixtesttask.network.GiphyApi
import kotlinx.coroutines.launch

// ViewModel class attached to the TrendingsFragment

class TrendingsViewModel : ViewModel() {

    private val _response = MutableLiveData<String>()

    val response: LiveData<String>
        get() = _response

    init {
        getGiphyTrendingProperties()
    }

    private fun getGiphyTrendingProperties() {

        viewModelScope.launch {
            try {
                val giphyModel = GiphyApi.retrofitService.getProperties()
                _response.value = giphyModel.data[0].images.original.url
            } catch (e: Exception) {
                _response.value = e.message
            }
        }

    }

}