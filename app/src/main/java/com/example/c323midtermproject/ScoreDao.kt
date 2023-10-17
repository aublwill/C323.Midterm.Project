package com.example.c323midtermproject

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

//Score Dao
@Dao
interface ScoreDao {
    @Insert
    suspend fun insert(score: Score)
    @Delete
    suspend fun delete(score: Score)
    @Update
    suspend fun update(score: Score)
    @Query("SELECT * FROM score_table WHERE scoreId = :key")
    fun get(key:Long): LiveData<Score>
    @Query("SELECT * FROM score_table ORDER BY score_score ASC")
    fun getAll(): LiveData<List<Score>>
}