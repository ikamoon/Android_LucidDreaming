package com.example.luciddreamingapp.data

data class Column(
    val id: String,
    val title: String,
    val content: String,
    val author: String,
    val publishDate: String,
    val readTime: Int // in minutes
)