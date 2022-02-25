package com.example.retrofitjoke

import android.app.Application
import com.example.retrofitjoke.api.ApiInterface
import com.example.retrofitjoke.api.ApiUtilities
import com.example.retrofitjoke.resporitory.JokesRepository
import com.example.retrofitjoke.room.JokesDatabase

class MyApplication:Application() {
    lateinit var jokesRepository: JokesRepository
    override fun onCreate() {
        super.onCreate()

        val apiInterface= ApiUtilities.getInstance().create(ApiInterface::class.java)
        val database=JokesDatabase.getDatabase(applicationContext)
        jokesRepository=JokesRepository(apiInterface,database)
    }
}