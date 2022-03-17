package com.example.retrofitjoke.resporitory

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.retrofitjoke.api.ApiInterface
import com.example.retrofitjoke.model.MyJoke
import com.example.retrofitjoke.room.JokesDatabase

class JokesRepository(private val apiInterface: ApiInterface,
private val jokesDatabase: JokesDatabase) {
    suspend fun getJokes(): MyJoke? {
        val result=apiInterface.getJoke()
        if (result.body()!=null){
            jokesDatabase.jokesDao().insertJokes(result.body()!!)
        }
        return result.body()
    }

    suspend fun getJokesFromDB(): List<MyJoke>? {
        var result:List<MyJoke>?
        result=jokesDatabase.jokesDao().getJokes()
        if (result.isNotEmpty()){
            return result
        }
        return null
    }

}