package com.example.newsapp.ui.activity

import android.content.Intent
import android.os.Bundle
import android.widget.ListView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.newsapp.viewmodel.NewsViewModel
import com.example.newsapp.R
import com.example.newsapp.ui.adapter.NewsAdapter

class MainActivity : AppCompatActivity() {
    private lateinit var listView: ListView
    private val newsViewModel: NewsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        listView = findViewById(R.id.listView)
        newsViewModel.fetchNews { newsList ->
            val adapter = NewsAdapter(this, newsList)
            listView.adapter = adapter

            listView.setOnItemClickListener { _, _, position, _ ->
                val intent = Intent(this@MainActivity, NewsActivity::class.java)
                intent.putExtra("url", newsList[position].href)
                startActivity(intent)
            }
        }
    }
}