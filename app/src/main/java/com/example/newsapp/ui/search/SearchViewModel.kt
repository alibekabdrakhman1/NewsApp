package com.example.newsapp.ui.search

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsapp.data.api.NewsRepository
import com.example.newsapp.models.NewsResponse
import com.example.newsapp.utils.Resource
import kotlinx.coroutines.launch
import retrofit2.http.Query
import javax.inject.Inject


class SearchViewModel @Inject constructor(private val repository: NewsRepository): ViewModel() {
//    val searchNewsLiveData: MutableLiveData<Resource<NewsResponse>> = MutableLiveData()
//    var searchNewsPage = 1
//
//    init {
//
//    }
//    fun getSearchNews(query: String) =
//        viewModelScope.launch {
//            searchNewsLiveData.postValue(Resource.Loading)
//        }
}