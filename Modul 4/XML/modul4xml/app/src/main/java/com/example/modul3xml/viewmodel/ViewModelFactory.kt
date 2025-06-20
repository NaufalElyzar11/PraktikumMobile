package com.example.modul3xml.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class ViewModelFactory(private val appName: String) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(RobloxGameViewModel::class.java)) {
            return RobloxGameViewModel(appName) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
} 