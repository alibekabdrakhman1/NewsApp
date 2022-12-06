package com.example.newsapp.data.api

import com.example.newsapp.data.db.ArticleDatabase
import javax.inject.Inject

class NewsRepository (val db: ArticleDatabase) {
    suspend fun getNews(countryCode: String, pageNumber: Int) =
        RetrofitConnect.api.getHeadlines(countryCode = countryCode,page = pageNumber)

    suspend fun getSearchNews(query: String, pageNumber: Int) =
        RetrofitConnect.api.getEverything(query = query, page = pageNumber)


}