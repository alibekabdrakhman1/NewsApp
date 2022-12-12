package com.example.newsapp.ui.main

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.newsapp.R
import com.example.newsapp.models.Article
import com.example.newsapp.ui.MainActivity
import com.example.newsapp.ui.NewsViewModel
import com.example.newsapp.ui.adapters.NewsAdapter
import com.example.newsapp.utils.Resource
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_favorite.*
import kotlinx.android.synthetic.main.fragment_main.*
import kotlinx.android.synthetic.main.item_article.*


class MainFragment : Fragment(R.layout.fragment_main) {

    lateinit var viewModel: NewsViewModel
    lateinit var newsAdapter: NewsAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = (activity as MainActivity).viewModel
        initAdapter()
        viewModel.mainNewsLiveData.observe(viewLifecycleOwner, Observer { response ->
            when(response) {
                is Resource.Success -> {
                    progress_bar.visibility = View.INVISIBLE
                    response.data?.let {
                        newsAdapter.differ.submitList(it.articles)
                    }
                }
                is Resource.Loading -> {
                    progress_bar.visibility = View.VISIBLE
                }
                is Resource.Error -> {
                    progress_bar.visibility = View.INVISIBLE
                    response.data?.let {
                        Log.e("Error","${it}")
                    }
                }

            }
        })

    }

    private fun initAdapter() {
        newsAdapter = NewsAdapter(object:NewsAdapter.Listener{
            override fun saveArticle(article: Article) {
                viewModel.saveNews(article = article)
                article.isSaved = true
                view?.let { Snackbar.make(it, "Article saved successfully", Snackbar.LENGTH_SHORT).show() }
            }

            override fun deleteArticle(article: Article) {
                viewModel.deleteNews(article = article)
                article.isSaved = false
                view?.let { Snackbar.make(it, "Article deleted successfully", Snackbar.LENGTH_SHORT).show() }
                viewModel.getFavoriteNews().value?.let { println(it.size) }

            }

            override fun openArticle(article: Article) {
                TODO("Not yet implemented")
            }

        })
        recycler_view_main.apply {
            adapter = newsAdapter
            layoutManager = LinearLayoutManager(activity)
        }
    }



}