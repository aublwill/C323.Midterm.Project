package com.example.c323midtermproject

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class MainViewModel(
    val dao: ScoreDao):ViewModel() {

    var newName = ""
    var newNumAttempts = 0

    private val _navigateToGame = MutableLiveData<Long?>()
    private val _navigateToScores = MutableLiveData<Long?>()

    val navigateToGame:LiveData<Long?>
        get() = _navigateToGame
    val navigateToScores:LiveData<Long?>
        get() = _navigateToScores


    /*
    @param none
    updates variable to trigger observer
     */
    fun onGameClicked(){
        _navigateToGame.value = 1
    }
    /*
    @param none
    resets variable to null
     */
    fun onGameNavigated(){
        _navigateToGame.value = null
    }
    /*
    @param none
    updates variable to trigger observer
     */
    fun onScoresClicked(){
        _navigateToScores.value = 1
    }
    /*
    @param none
    resets variable to null
     */
    fun onScoresNavigated(){
        _navigateToScores.value = null
    }

}