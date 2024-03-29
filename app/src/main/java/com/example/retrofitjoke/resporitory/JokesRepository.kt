package com.example.retrofitjoke.resporitory

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.retrofitjoke.api.ApiInterface
import com.example.retrofitjoke.model.MyJoke
import com.example.retrofitjoke.room.JokesDatabase

class JokesRepository(private val apiInterface: ApiInterface,
private val jokesDatabase: JokesDatabase) {
    private val jokesLiveData=MutableLiveData<MyJoke>()

    val jokes:LiveData<MyJoke>
    get() = jokesLiveData

    suspend fun getJokes(){
        val result=apiInterface.getJoke()
        if (result.body()!=null){

            jokesDatabase.jokesDao().insertJokes(result.body()!!)
            jokesLiveData.postValue(result.body())
        }
    }

}