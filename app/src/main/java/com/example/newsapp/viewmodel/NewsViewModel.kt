package com.example.newsapp.viewmodel

import androidx.lifecycle.ViewModel
import com.example.newsapp.model.News
import com.example.newsapp.repository.NewsRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NewsViewModel : ViewModel() {
    private val newsRepository = NewsRepository()

    fun fetchNews(callback: (List<News>) -> Unit) {
        CoroutineScope(Dispatchers.Main).launch {
            val newsList = newsRepository.fetchNews()
            callback(newsList)
        }
    }
}