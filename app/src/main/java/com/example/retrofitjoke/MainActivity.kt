package com.example.retrofitjoke

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.widget.ViewPager2
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

class MainActivity : AppCompatActivity() {

    private lateinit var jokesViewModel: JokesViewModel
    private lateinit var jokesRepository: JokesRepository
   private lateinit var viewPager: ViewPager2
    private lateinit var jokesArrayList: ArrayList<MyJoke>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewPager=findViewById(R.id.viewPager)

        val apiInterface = ApiUtilities.getInstance().create(ApiInterface::class.java)
        val database = JokesDatabase.getDatabase(applicationContext)
        jokesRepository = JokesRepository(apiInterface, database)
        val repository = jokesRepository


        jokesViewModel =
            ViewModelProvider(this, JokesViewModelFactory(repository))[JokesViewModel::class.java]
        jokesArrayList = arrayListOf()

       lateinit var result: MyJoke
        CoroutineScope(Main).launch {
            result= jokesViewModel.updateJokes()!!
            jokesArrayList = (jokesViewModel.getJokes() as ArrayList<MyJoke>?)!!
            Log.i("Main", jokesArrayList.toString())

            jokesArrayList.sortBy {
                it.updated_at
            }
            val adapter = MyAdapter(jokesArrayList)
            viewPager.adapter=adapter
        }


    }

}