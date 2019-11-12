package com.brainplow.mvvmsampleapp.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.brainplow.mvvmsampleapp.data.db.entities.User

@Database(
    entities = [User::class],
    version = 1
)
abstract class AppDataBase:RoomDatabase(){

    abstract fun getUserDao():UserDao

    companion object{

        @Volatile
        private var instance:AppDataBase?=null
        private val LOCK=Any()
        operator fun invoke(context: Context)= instance?: synchronized(LOCK){
            instance?:buildDataBase(context).also {
                instance=it
            }
        }

        private fun buildDataBase(context: Context) =
            Room.databaseBuilder(context.applicationContext,
                AppDataBase::class.java,
                "MyDataBase.db"
            ).build()
    }
}