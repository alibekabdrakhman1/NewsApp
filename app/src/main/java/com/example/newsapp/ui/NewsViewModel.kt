package com.example.newsapp.ui

import android.content.Intent
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsapp.data.api.NewsRepository
import com.example.newsapp.models.Article
import com.example.newsapp.models.NewsResponse
import com.example.newsapp.ui.details.DetailsFragment
import com.example.newsapp.utils.Resource
import kotlinx.coroutines.launch
import kotlin.reflect.jvm.internal.impl.protobuf.Internal

class NewsViewModel(private val newsRepository: NewsRepository): ViewModel() {
    val mainNewsLiveData: MutableLiveData<Resource<NewsResponse>> = MutableLiveData()
    var newsPage = 1
    val searchNewsLiveData: MutableLiveData<Resource<NewsResponse>> = MutableLiveData()

    init {
        getNews("us")
    }

    private fun getNews(countryCode: String) =
        viewModelScope.launch {
            mainNewsLiveData.postValue(Resource.Loading())
            val response = newsRepository.getNews(countryCode = countryCode, pageNumber = newsPage)
            if (response.isSuccessful) {
                response.body().let { res ->
                    mainNewsLiveData.postValue(Resource.Success(res))
                }
            } else {
                mainNewsLiveData.postValue(Resource.Error(message = response.message()))
            }
        }

    fun getSearchNews(query: String) =
        viewModelScope.launch {
            searchNewsLiveData.postValue(Resource.Loading())
            val response = newsRepository.getSearchNews(query = query, pageNumber = newsPage)
            if (response.isSuccessful) {
                response.body().let { res ->
                    searchNewsLiveData.postValue(Resource.Success(res))
                }
            } else {
                searchNewsLiveData.postValue(Resource.Error(message = response.message()))
            }
        }

    fun getFavoriteNews() = newsRepository.getFavoriteNews()



    fun saveNews(article: Article) = viewModelScope.launch {
        newsRepository.saveNews(article = article)
        article.isSaved = true
    }
    fun deleteNews(article: Article) = viewModelScope.launch {
        newsRepository.deleteNews(article = article)
        article.isSaved = false
    }
    fun openNews(article: Article) = viewModelScope.launch {

    }
}