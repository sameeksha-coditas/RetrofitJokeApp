package com.example.retrofitjoke.api

import com.example.retrofitjoke.model.MyJoke

import retrofit2.Response
import retrofit2.http.GET

interface ApiInterface {
    @GET("/jokes/random")
    suspend fun getJoke(): Response<MyJoke>
}