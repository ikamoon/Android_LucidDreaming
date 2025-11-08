package com.aura_weavers.luciddreaming.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GetBootstrapResponse (
    val column: Column,
    val video: DreamInductionVideo,
    @SerialName("line_banner_image_url") val lineBannerImageURL: String,
    @SerialName("line_url") val lineURL: String,
    @SerialName("tou_url") val touURL: String,
    @SerialName("pp_url") val ppURL: String,
    @SerialName("dream_diagnosis_prompt_system_message") val dreamDiagnosisPromptSystemMessage: String,
    @SerialName("dream_diagnosis_prompt_user_message1") val dreamDiagnosisPromptUserMessage1: String,
    @SerialName("dream_diagnosis_prompt_user_message2") val dreamDiagnosisPromptUserMessage2: String,
    @SerialName("is_subscripting") val isSubscripting: Boolean,
    @SerialName("within_dream_diagnosis_limit") val withinDreamDiagnosisLimit: Boolean,
    @SerialName("launch_modal_frequency") val launchModalFrequency: Int?,
    @SerialName("launch_modal_title_text") val launchModalTitleText: String?,
    @SerialName("launch_modal_image_url") val launchModalImageURL: String?,
    @SerialName("launch_modal_body_text") val launchModalBodyText: String?,
    @SerialName("launch_modal_button_text") val launchModalButtonText: String?,
    @SerialName("launch_modal_button_url") val launchModalButtonURL: String?
)