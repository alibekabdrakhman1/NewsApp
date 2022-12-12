package com.example.newsapp.data.api

import com.example.newsapp.data.db.ArticleDatabase
import com.example.newsapp.models.Article
import javax.inject.Inject

class NewsRepository (val db: ArticleDatabase) {
    suspend fun getNews(countryCode: String, pageNumber: Int) =
        RetrofitConnect.api.getHeadlines(countryCode = countryCode,page = pageNumber)

    suspend fun getSearchNews(query: String, pageNumber: Int) =
        RetrofitConnect.api.getEverything(query = query, page = pageNumber)

    fun getFavoriteNews() = db.getArticleDao().getAllArticles()

    suspend fun saveNews(article: Article) = db.getArticleDao().insert(article = article)

    suspend fun deleteNews(article: Article) = db.getArticleDao().deleteNews(article = article)

    suspend fun clearDb() = db.getArticleDao().clearDb()

}