package com.example.retrofitjoke

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.retrofitjoke.adapter.MyAdapter
import com.example.retrofitjoke.api.ApiInterface
import com.example.retrofitjoke.api.ApiUtilities
import com.example.retrofitjoke.model.MyJoke
import com.example.retrofitjoke.resporitory.JokesRepository
import com.example.retrofitjoke.room.JokesDatabase
import com.example.retrofitjoke.viewmodel.JokesViewModel
import com.example.retrofitjoke.viewmodel.JokesViewModelFactory
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    private lateinit var jokesViewModel: JokesViewModel
    lateinit var jokesRepository: JokesRepository
    private lateinit var recyclerview: RecyclerView
    private lateinit var jokesArrayList: ArrayList<MyJoke>

    //private lateinit var apiInterface: ApiInterface
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val apiInterface = ApiUtilities.getInstance().create(ApiInterface::class.java)
        val database = JokesDatabase.getDatabase(applicationContext)
        jokesRepository = JokesRepository(apiInterface, database)
        val repository = jokesRepository


        jokesViewModel =
            ViewModelProvider(this, JokesViewModelFactory(repository))[JokesViewModel::class.java]
//        jokesViewModel.jokes.observe(this) {
//            Log.d("Main", it.toString())
//        }

        recyclerview = findViewById(R.id.idRVNotes)
        recyclerview.layoutManager = LinearLayoutManager(this)
        recyclerview.setHasFixedSize(true)

        jokesArrayList = arrayListOf()

        //lateinit var result: List<MyJoke>
        CoroutineScope(Main).launch {
            jokesArrayList = (jokesViewModel.getJokes() as ArrayList<MyJoke>?)!!
            Log.i("Main", jokesArrayList.toString())

            jokesArrayList.sortBy {
                it.created_at
            }
            val adapter = MyAdapter(jokesArrayList)
            recyclerview.adapter = adapter


        }

    }
}