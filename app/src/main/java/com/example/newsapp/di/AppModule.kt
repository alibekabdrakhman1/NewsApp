package com.example.newsapp.di

import com.example.newsapp.data.api.NewsService
import com.example.newsapp.utils.Constants.Companion.BASE_URL
import com.squareup.moshi.FromJson
import com.squareup.moshi.JsonReader
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class AppModule {
//    object NULL_TO_EMPTY_STRING_ADAPTER {
//        @FromJson
//        fun fromJson(reader: JsonReader): String {
//            if (reader.peek() != JsonReader.Token.NULL) {
//                return reader.nextString()
//            }
//            reader.nextNull<Unit>()
//            return ""
//        }
//    }
//    private val moshi = Moshi.Builder()
//        .add(AppModule.NULL_TO_EMPTY_STRING_ADAPTER)
//        .add(KotlinJsonAdapterFactory())
//        .build()

    @Provides
    fun baseUrl() = BASE_URL


    @Provides
    @Singleton
    fun provideRetrofit(baseUrl: String): NewsService =
        Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(NewsService::class.java)
}