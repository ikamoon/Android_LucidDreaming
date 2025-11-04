package com.aura_weavers.luciddreaming.data

data class TaskCardData(
    val index: Int,
    val title: String,
    val subtitle: String,
    val duration: String,
    val type: String,
    val icon: String,
    val isEnabled: Boolean = true,
    val hasShareButton: Boolean = false
)