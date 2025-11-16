package com.aura_weavers.luciddreaming.model

import kotlinx.serialization.Serializable

@Serializable
data class Column(
    val id: Int,
    val title: String,
    val imageUrl: String,
    val contentUrl: String,
    val isPremium: Boolean
)