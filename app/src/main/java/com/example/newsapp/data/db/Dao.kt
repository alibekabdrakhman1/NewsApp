package com.example.newsapp.data.db

import androidx.lifecycle.LiveData
import androidx.room.Query
import com.example.newsapp.models.Article

interface Dao {
    @Query("SELECT * FROM articles")
    suspend fun getAllArticles(): LiveData<List<Article>>
}