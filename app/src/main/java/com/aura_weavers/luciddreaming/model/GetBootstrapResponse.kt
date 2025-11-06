package com.aura_weavers.luciddreaming.model

import com.example.luciddreamingapp.data.Column
import com.example.luciddreamingapp.data.DreamInductionVideo

data class GetBootstrapResponse(
    val column: Column,
    val video: DreamInductionVideo,
    val lineBannerImageURL: String,
    val lineURL: String,
    val touURL: String,
    val ppURL: String,
    val dreamDiagnosisPromptSystemMessage: String,
    val dreamDiagnosisPromptUserMessage1: String,
    val dreamDiagnosisPromptUserMessage2: String,
    val isSubscripting: Boolean,
    val withinDreamDiagnosisLimit: Boolean,
    val launchModalFrequency: Int?,
    val launchModalTitleText: String?,
    val launchModalImageURL: String?,
    val launchModalBodyText: String?,
    val launchModalButtonText: String?,
    val launchModalButtonURL: String?
)