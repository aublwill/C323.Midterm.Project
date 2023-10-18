package com.example.c323midtermproject

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

//Score class
@Entity(tableName = "score_table")
data class Score (
    @PrimaryKey(autoGenerate = true)
    var scoreId: Long = 0L,
    @ColumnInfo(name = "score_name")
    var scoreName:String = "",
    @ColumnInfo(name = "score_score")
    var scoreScore:String = "0"
)