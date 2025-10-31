package com.example.luciddreamingapp.data

data class DreamInductionVideo(
    val id: String,
    val title: String,
    val description: String,
    val videoUrl: String,
    val thumbnailUrl: String,
    val duration: Int // in seconds
)
