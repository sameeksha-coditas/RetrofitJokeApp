package com.example.retrofitjoke.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.retrofitjoke.R
import com.example.retrofitjoke.model.MyJoke

class MyAdapter(private val jokeList: ArrayList<MyJoke>) : RecyclerView.Adapter<MyAdapter.MyViewHolder>() {


    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)  {
        val joke: TextView = itemView.findViewById(R.id.jokeText)
        val jokeCreatedAt: TextView = itemView.findViewById(R.id.jokeCreated)
       // val delete: ImageView = itemView.findViewById(R.id.idIVDelete)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.list_item,
            parent,
            false
        )
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = jokeList[position]
        holder.joke.text = currentItem.value
        holder.jokeCreatedAt.text = currentItem.created_at
    }

    override fun getItemCount(): Int {
       return jokeList.size
    }
}