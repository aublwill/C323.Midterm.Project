package com.example.c323midtermproject

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import java.lang.IllegalArgumentException

class GameViewModelFactory (
                                private val dao: ScoreDao) : ViewModelProvider.Factory {

    /*
    create and return instance of GameViewModel
     */
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(GameViewModel::class.java)){
            return GameViewModel(dao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel")
    }
}