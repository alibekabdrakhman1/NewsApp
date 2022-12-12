package com.example.newsapp.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.example.newsapp.R
import com.example.newsapp.models.Article
import com.example.newsapp.ui.MainActivity
import kotlinx.android.synthetic.main.item_article.view.*
import kotlinx.coroutines.launch

class NewsAdapter(val listener: Listener) : RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {
    inner class NewsViewHolder(view: View): RecyclerView.ViewHolder(view)
    private val callback = object : DiffUtil.ItemCallback<Article>() {
        override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem.url == newItem.url
        }
        override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem == newItem
        }
    }
    val differ = AsyncListDiffer(this, callback)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        return NewsViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_article, parent, false)
        )
    }

    interface Listener {
        fun saveArticle(article: Article)
        fun deleteArticle(article: Article)
        fun openArticle(article: Article)
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val article = differ.currentList[position]
        holder.itemView.apply {
            Glide.with(this).load(article.urlToImage).into(item_image)
            item_image.clipToOutline = true
            item_text.text = article.title
            item_date.text = article.publishedAt
            favBtn.setOnClickListener{
                    if (!article.isSaved) {
                        listener.saveArticle(article = article)
                        favBtn.setBackgroundResource(R.drawable.ic_favorite)
                        article.isSaved = true
                    } else {
                        listener.deleteArticle(article = article)
                        favBtn.setBackgroundResource(R.drawable.no_fav)
                        article.isSaved = false
                    }
            }
            if(article.isSaved) favBtn.setBackgroundResource(R.drawable.ic_favorite)
            else favBtn.setBackgroundResource(R.drawable.no_fav)
            rootView.setOnClickListener {
                if (!article.isSaved) {
                    listener.openArticle(article)
                }
            }
        }
    }


    override fun getItemCount(): Int {
        return this.differ.currentList.size
    }

    private var onItemClickListener: ((Article) -> Unit)? = null

    fun setOnItemClickListener(listener: (Article) -> Unit) {
        onItemClickListener = listener
    }



}