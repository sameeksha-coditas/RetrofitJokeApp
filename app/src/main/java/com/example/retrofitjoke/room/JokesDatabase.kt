package com.example.retrofitjoke.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.retrofitjoke.model.MyJoke

@Database(entities = [MyJoke::class], version = 1)
abstract class JokesDatabase:RoomDatabase() {

    abstract fun jokesDao():RoomDao

    companion object{
        private var INSTANCE:JokesDatabase?=null

        fun getDatabase(context: Context):JokesDatabase{

            if(INSTANCE==null){
                INSTANCE= Room.databaseBuilder(
                    context,JokesDatabase::class.java,
                    "jokesDB"
                ).build()
            }

            return INSTANCE!!
        }
    }
}