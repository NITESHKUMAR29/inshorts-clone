package com.example.news

data class Article(
    val author: String,
    val content: String? = null,
    val description: String,
    val publishedAt: String,
    val source: Source,
    val title: String,
    val url: String,
    val urlToImage: String
)

data class News(
    val articles: List<Article>,
    val totalResults: Int
)

data class Source(
    val id: Any,
    val name: String
)