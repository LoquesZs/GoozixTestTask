package com.example.goozixtesttask.ui.trendings

import androidx.lifecycle.*
import com.example.goozixtesttask.network.DEFAULT_LIMIT
import com.example.goozixtesttask.network.Data
import com.example.goozixtesttask.network.GiphyApi
import kotlinx.coroutines.launch

enum class GiphyApiStatus { LOADING, ERROR, DONE, NO_MATCHES }

// ViewModel class attached to the TrendingsFragment

class TrendingsViewModel : ViewModel() {

    private enum class LastRequest {
        TRENDING, SEARCH
    } // Enum Class used to remember the last request to Giphy

    val _currentSearchPage = MutableLiveData<Int>() // current "page" in Giphy response data[]

    private val maxPage = MutableLiveData<Int>() // max available page in Giphy response data[]

    private lateinit var lastRequest: LastRequest // last request category

    private val _status = MutableLiveData<GiphyApiStatus>() // Observable status of GiphyAPI
    val status: LiveData<GiphyApiStatus>
        get() = _status

    val searchRequest = MutableLiveData<String>() // Search Request string

    private val _models = MutableLiveData<List<Data>>() // Data fetched from GiphyAPI
    val models: LiveData<List<Data>>
        get() = _models

    init {
        _currentSearchPage.value = 0
        getGiphyTrendingModel()
    }
    /** Get trending GIFs from Giphy **/
    fun getGiphyTrendingModel(page: Int = 0) {
        lastRequest = LastRequest.TRENDING
        _status.value = GiphyApiStatus.LOADING
        viewModelScope.launch {
            try {
                val response = GiphyApi.retrofitService.getTrendingModel(
                    offset = DEFAULT_LIMIT * page
                )
                _models.value = response.gifList
                maxPage.value = (response.pagination.total / DEFAULT_LIMIT) + 1
                _status.value = GiphyApiStatus.DONE
            } catch (e: Exception) {
                _status.value = GiphyApiStatus.ERROR
                _models.value = ArrayList()
            }
        }
    }

    /** Get GIFs revelant to search request **/
    fun getGiphySearchModel(page: Int = 0) {
        lastRequest = LastRequest.SEARCH
        _status.value = GiphyApiStatus.LOADING
        viewModelScope.launch {
            try {
                if(GiphyApi.retrofitService.getSearchModel(
                        searchRequest = searchRequest.value!!,
                        offset = DEFAULT_LIMIT * page
                    ).gifList.isEmpty()){
                    _status.value = GiphyApiStatus.NO_MATCHES
                    _models.value = ArrayList()
                } else {
                    val response = GiphyApi.retrofitService
                        .getSearchModel(
                            searchRequest = searchRequest.value!!,
                            offset = DEFAULT_LIMIT * page
                        )
                    _models.value = response.gifList
                    maxPage.value = (response.pagination.total / DEFAULT_LIMIT) + 1
                    _status.value = GiphyApiStatus.DONE
                }
            } catch (e: Exception) {
                _status.value = GiphyApiStatus.ERROR
                _models.value = ArrayList()
            }
        }
    }

    // Methods for pagination
    /** Function that increases current page value **/
    fun nextPage() {
        if (_currentSearchPage.value!! < maxPage.value!!) {
            _currentSearchPage.value = _currentSearchPage.value?.plus(1)
            getAppropriateModel()
        }
    }

    /** Function that decreases current page value **/
    fun prevPage() {
        if(_currentSearchPage.value!! > 0) {
            _currentSearchPage.value = _currentSearchPage.value?.minus(1)
            getAppropriateModel()
        }
    }

    private fun getAppropriateModel() {
        when (lastRequest) {
            LastRequest.TRENDING -> {
                _currentSearchPage.value?.let { getGiphyTrendingModel(it) }
            }
            LastRequest.SEARCH -> {
                _currentSearchPage.value?.let { getGiphySearchModel(it) }
            }
        }
    }

    fun searchPageReset() {
        _currentSearchPage.value = 0
    }
}