package com.aura_weavers.luciddreaming.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DreamInductionVideo(
    val id: Int,
    val title: String//,
//    @SerialName("video_url") val videoUrl: String,
//    @SerialName("image_url") val imageUrl: String,
//    @SerialName("is_premium") val isPremium: Boolean
)