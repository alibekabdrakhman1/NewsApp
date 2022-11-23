package com.example.newsapp.data.api

import retrofit2.http.Query
import retrofit2.http.GET

interface NewsService {
    @GET("/v2/everything")
    suspend fun getEverything (
        @Query("q") query: String,
        @Query("page") page: Int = 1,
        @Query("apiKey") apiKey: String = "cfdce2a3efee4961a40e6bdf412f6758"
    )
    suspend fun getHeadlines (
        @Query("country") countryCode: String = "ua",
        @Query("page") page: Int = 1,
        @Query("apiKey") apiKey: String = "cfdce2a3efee4961a40e6bdf412f6758"
    )

}