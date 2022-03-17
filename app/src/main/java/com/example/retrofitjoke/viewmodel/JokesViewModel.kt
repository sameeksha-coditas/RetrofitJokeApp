package com.example.retrofitjoke.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.retrofitjoke.model.MyJoke
import com.example.retrofitjoke.resporitory.JokesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class JokesViewModel(private val jokesRepository: JokesRepository):ViewModel() {
    init {
        viewModelScope.launch(Dispatchers.IO) {

        }
    }
    suspend fun updateJokes(): MyJoke? {
        return jokesRepository.getJokes()
    }

    suspend fun getJokes(): List<MyJoke>? {
       return jokesRepository.getJokesFromDB()
   }
}