package com.aura_weavers.luciddreaming.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Column(
    val id: Int,
    val title: String
//    ,
//    @SerialName("is_premium") val isPremium: Boolean
)