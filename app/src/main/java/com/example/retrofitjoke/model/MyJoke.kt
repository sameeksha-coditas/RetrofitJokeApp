package com.example.retrofitjoke.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Jokes")
data class MyJoke(
    //val categories: MutableList<Any>,
    val created_at: String,
    val icon_url: String,
    @PrimaryKey
    val id: String,
    val updated_at: String,
    val url: String,
    val value: String
)