package com.example.c323midtermproject

import androidx.recyclerview.widget.DiffUtil

class ScoreDiffItem : DiffUtil.ItemCallback<Score>() {

    //determine if two items or contents are the same
    override fun areItemsTheSame(oldItem: Score, newItem: Score)
            = (oldItem.scoreId == newItem.scoreId)

    override fun areContentsTheSame(oldItem: Score, newItem: Score)
            = (oldItem == newItem)
}