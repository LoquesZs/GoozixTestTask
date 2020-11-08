package com.example.goozixtesttask.ui.trendings

import androidx.lifecycle.*
import com.example.goozixtesttask.network.Data
import com.example.goozixtesttask.network.GiphyApi
import kotlinx.coroutines.launch

enum class GiphyApiStatus { LOADING, ERROR, DONE, NO_MATCHES }

// ViewModel class attached to the TrendingsFragment

class TrendingsViewModel : ViewModel() {

    private val _status = MutableLiveData<GiphyApiStatus>()
    val status: LiveData<GiphyApiStatus>
        get() = _status

    val searchRequest = MutableLiveData<String>()

    private val _models = MutableLiveData<List<Data>>()
    val models: LiveData<List<Data>>
        get() = _models

    init {
        getGiphyTrendingModel()
    }

    fun getGiphyTrendingModel() {
        _status.value = GiphyApiStatus.LOADING
        viewModelScope.launch {
            try {
                _models.value = GiphyApi.retrofitService.getTrendingModel().gifList
                _status.value = GiphyApiStatus.DONE
            } catch (e: Exception) {
                _status.value = GiphyApiStatus.ERROR
                _models.value = ArrayList()
            }
        }

    }

    fun getGiphySearchModel() {
        _status.value = GiphyApiStatus.LOADING
        viewModelScope.launch {
            try {
                if(GiphyApi.retrofitService.getSearchModel(searchRequest.value!!).gifList.isEmpty()){
                    _status.value = GiphyApiStatus.NO_MATCHES
                    _models.value = ArrayList()
                } else {
                    _models.value = GiphyApi.retrofitService
                        .getSearchModel(searchRequest.value!!)
                        .gifList
                    _status.value = GiphyApiStatus.DONE
                }
            } catch (e: Exception) {
                _status.value = GiphyApiStatus.ERROR
                _models.value = ArrayList()
            }
        }
    }
}