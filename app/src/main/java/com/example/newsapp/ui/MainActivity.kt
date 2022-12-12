package com.example.newsapp.ui

import android.content.Intent
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
import com.example.newsapp.databinding.ActivityMainBinding
import com.example.newsapp.ui.details.DetailsFragment
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity() : AppCompatActivity() {

    lateinit var viewModel: NewsViewModel
    private var _binding: ActivityMainBinding? = null
    private val mBinding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_splash)
        CoroutineScope(Dispatchers.Main).launch {
            delay(5000)
            _binding = ActivityMainBinding.inflate(layoutInflater)
            setContentView(mBinding.root)

            bottom_nav_menu.setupWithNavController(
                navController = nav_host_fragment.findNavController()
            )
        }
        val newsRepository = NewsRepository(ArticleDatabase(this))
        val viewModelProvideFactory = NewsViewModelFactory(newsRepository)
        viewModel = ViewModelProvider(this, viewModelProvideFactory)[NewsViewModel::class.java]




    }

}