package com.example.newsapp.data.db

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.newsapp.models.Article

abstract class Database: RoomDatabase() {
    abstract fun getDao(): Dao

    companion object {
        @Volatile
        private var instance: Database? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance?: synchronized(LOCK) {
             instance ?: createDatabase(context).also { instance = it }
        }

        private fun createDatabase(context: Context): Database {
            return Room.databaseBuilder(
                context.applicationContext,
                Database::class.java,
                "database"
            ).build()
        }
    }
}
