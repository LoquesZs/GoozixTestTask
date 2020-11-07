package com.example.goozixtesttask.ui.trendings

import android.util.Log
import androidx.lifecycle.*
import com.example.goozixtesttask.network.Data
import com.example.goozixtesttask.network.GiphyApi
import kotlinx.coroutines.launch

// ViewModel class attached to the TrendingsFragment

class TrendingsViewModel : ViewModel() {

    private val _response = MutableLiveData<String>()
    val response: LiveData<String>
        get() = _response

    val searchRequest = MutableLiveData<String>()

    private val _models = MutableLiveData<List<Data>>()
    val models: LiveData<List<Data>>
        get() = _models

    init {
        getGiphyTrendingModel()
    }

    private fun getGiphyTrendingModel() {

        viewModelScope.launch {
            try {
                _models.value = GiphyApi.retrofitService.getTrendingModel().gifList
                _response.value = "Data retrieved"
            } catch (e: Exception) {
                _response.value = e.message
            }
        }

    }

    fun getGiphySearchModel() {
        viewModelScope.launch {
            try {
                _models.value = searchRequest.value?.let {
                    GiphyApi.retrofitService.getSearchModel(it).gifList
                }
            } catch (e: Exception) {
                _response.value = e.message
                Log.d("VMGGSM", _response.value ?: "Null")
            }
        }
    }

}