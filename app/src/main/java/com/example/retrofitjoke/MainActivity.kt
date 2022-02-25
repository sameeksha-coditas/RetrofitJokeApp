package com.example.retrofitjoke

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import com.example.retrofitjoke.api.ApiInterface
import com.example.retrofitjoke.api.ApiUtilities
import com.example.retrofitjoke.resporitory.JokesRepository
import com.example.retrofitjoke.viewmodel.JokesViewModel
import com.example.retrofitjoke.viewmodel.JokesViewModelFactory
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    private lateinit var jokesViewModel: JokesViewModel
    //private lateinit var apiInterface: ApiInterface
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

       val repository=(application as MyApplication).jokesRepository


       jokesViewModel= ViewModelProvider(this,JokesViewModelFactory(repository))[JokesViewModel::class.java]
        jokesViewModel.jokes.observe(this) {
            Log.d("Main", it.toString())
        }


    }

}