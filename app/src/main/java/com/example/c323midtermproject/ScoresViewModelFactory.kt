package com.example.c323midtermproject

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class ScoresViewModelFactory (private val dao:ScoreDao)
    :ViewModelProvider.Factory{

    //create and return ScoresViewModel instance
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ScoresViewModel::class.java)){
            return ScoresViewModel(dao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel")
    }
}