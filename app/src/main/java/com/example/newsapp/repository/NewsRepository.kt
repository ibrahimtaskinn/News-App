package com.example.newsapp.repository

import com.example.newsapp.model.News
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.jsoup.select.Elements

class NewsRepository {

    suspend fun fetchNews(): List<News> = withContext(Dispatchers.IO) {
        val arr = mutableListOf<News>()
        val url = "https://www.haberler.com/"
        val doc: Document = Jsoup.connect(url).timeout(15000).get()
        val elements: Elements = doc.getElementsByAttribute("data-headlinenumber")
        for (item in elements) {
            val img = item.getElementsByTag("img")

            val href = item.attr("abs:href")
            val src = img.attr("data-src")
            val title = img.attr("alt")

            if (title != "" && href != "" && src != "") {
                val news = News(title, src, href)
                arr.add(news)
            }
        }
        return@withContext arr
    }
}