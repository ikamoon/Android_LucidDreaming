package com.aura_weavers.luciddreaming.model

import kotlinx.serialization.Serializable

@Serializable
data class DreamInductionVideo(
    val id: Int,
    val title: String,
    val videoUrl: String,
    val imageUrl: String,
    val isPremium: Boolean
)