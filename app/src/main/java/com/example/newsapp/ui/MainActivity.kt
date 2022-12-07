package com.example.newsapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Parcel
import android.os.Parcelable
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.newsapp.R
import com.example.newsapp.data.api.NewsRepository
import com.example.newsapp.data.db.ArticleDatabase
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity() : AppCompatActivity() {

    lateinit var viewModel: NewsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val newsRepository = NewsRepository(ArticleDatabase(this))
        val viewModelProvideFactory = NewsViewModelFactory(newsRepository)
        viewModel = ViewModelProvider(this, viewModelProvideFactory)[NewsViewModel::class.java]
        bottom_nav_menu.setupWithNavController(
            navController = nav_host_fragment.findNavController()
        )
    }

}