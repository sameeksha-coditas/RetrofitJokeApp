package com.example.retrofitjoke.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.retrofitjoke.resporitory.JokesRepository

class JokesViewModelFactory( private val jokesRepository: JokesRepository):ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return JokesViewModel(jokesRepository) as T
    }
}