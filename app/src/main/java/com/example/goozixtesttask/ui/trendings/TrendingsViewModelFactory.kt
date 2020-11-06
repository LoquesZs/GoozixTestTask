package com.example.goozixtesttask.ui.trendings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class TrendingsViewModelFactory : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(TrendingsViewModel::class.java)) {
            return TrendingsViewModel() as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}