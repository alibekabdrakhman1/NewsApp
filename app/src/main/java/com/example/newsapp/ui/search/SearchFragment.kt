package com.example.newsapp.ui.search

import android.os.Bundle
import android.text.Editable
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.newsapp.databinding.FragmentSearchBinding
import com.example.newsapp.ui.adapters.NewsAdapter
import com.example.newsapp.utils.Resource
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_main.*
import kotlinx.android.synthetic.main.fragment_search.*
import kotlinx.coroutines.Job
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SearchFragment : Fragment() {
//
//    private var _binding: FragmentSearchBinding? = null
//    private val mBinding get() = _binding!!
//
//    lateinit var newsAdapter: NewsAdapter
//
//
//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//        initAdapter()
//        search_input.addTextChangedListener { text: Editable? ->
//                text?.let {
//                    if (it.toString().isNotEmpty()) {
//                        viewModel.getSearchNews(query = it.toString())
//                    }
//                }
//        }
//
//        viewModel.searchNewsLiveData.observe(viewLifecycleOwner) { response ->
//            when(response) {
//                is Resource.Success -> {
//                    progress_bar_search.visibility = View.INVISIBLE
//                    response.data?.let {
//                        newsAdapter.differ.submitList(it.articles)
//                    }
//                }
//                is Resource.Loading -> {
//                    progress_bar_search.visibility = View.VISIBLE
//                }
//                is Resource.Error -> {
//                    progress_bar_search.visibility = View.INVISIBLE
//                    response.data?.let {
//                        Log.e("Error","$it")
//                    }
//                }
//
//            }
//        }
//    }
//
//    private fun initAdapter() {
//        newsAdapter = NewsAdapter()
//        recycler_view_search.apply {
//            adapter = newsAdapter
//            layoutManager = LinearLayoutManager(activity)
//        }
//    }
}