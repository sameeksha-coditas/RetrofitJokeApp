package com.example.retrofitjoke.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.retrofitjoke.model.MyJoke

@Dao
interface RoomDao {
    @Insert
    suspend fun insertJokes(joke: MyJoke)

    @Query("SELECT * FROM Jokes")
    suspend fun getJokes():List<MyJoke>
}