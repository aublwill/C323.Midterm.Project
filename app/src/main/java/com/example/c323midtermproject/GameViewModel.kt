package com.example.c323midtermproject

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import java.util.Random

class GameViewModel(  val dao: ScoreDao):ViewModel() {

    //variables
    private val _navigateToMain = MutableLiveData<Boolean>(false)
    val navigateToMain:LiveData<Boolean>
        get() = _navigateToMain

    val score = Score()

    /*
    @param none
    saves and updates current score
     */
    fun saveScore(){
        viewModelScope.launch {
            dao.insert(score)
            dao.update(score)
            _navigateToMain.value = true
        }
    }
    /*
    @param none
    resets value to false
     */
    fun onNavigatedToMain(){
        _navigateToMain.value = false
    }
    /*
    @param none
    updates number of attempts in database
     */
    fun attempts(){
        viewModelScope.launch {
            score.scoreScore= (score.scoreScore.toInt()+1).toString()
        }
    }
    /*
    @param num:Int the number to increase
    increases number by one
     */
    fun increase(num:Int):Int{
        return num + 1
    }
    /*
    @param num:Int the number to decrease
    decreases number by one
    */
    fun decrease(num:Int):Int{
        return num - 1
    }
    /*
    num: random number between 1-100
    @param guess:Int number that was guessed
    returns string that compares guess to random picked number
     */
    val num = (1 until 101).random()
    fun guess(guess:Int):String{
        if (guess==num)
            return "win"
        else if (guess<num)
            return "higher"
        else return "lower"
    }
}