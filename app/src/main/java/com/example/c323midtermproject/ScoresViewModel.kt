package com.example.c323midtermproject

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class ScoresViewModel (val dao:ScoreDao):ViewModel(){

    //variables
    val scores = dao.getAll()

    private val _navigateToMain = MutableLiveData<Boolean>(false)
    val navigateToMain: LiveData<Boolean>
        get() = _navigateToMain

    /*
    @param none
    changes variable to trigger observer
     */
    fun navigateToMain(){
        _navigateToMain.value = true
    }
    /*
    @param none
    resets variable to false
     */
    fun onNavigatedToMain(){
        _navigateToMain.value = false
    }
    /*
    @param scoreId:Long current scores id
    deletes score using id from the database
     */
    fun deleteById(scoreId:Long){
        viewModelScope.launch {
            val score = Score()
            score.scoreId = scoreId
            dao.delete(score)
        }
    }
}