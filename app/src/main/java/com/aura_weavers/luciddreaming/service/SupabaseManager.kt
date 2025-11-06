package com.aura_weavers.luciddreaming.service

import com.aura_weavers.luciddreaming.model.GetBootstrapResponse

object SupabaseManager {
    suspend fun fetchBootstrap(): GetBootstrapResponse {
        // TODO: Implement actual fetching logic from Supabase
        return GetBootstrapResponse(
            column = null,
            video = null,
            lineBannerImageURL = "",
            lineURL = "",
            touURL = "",
            ppURL = "",
            dreamDiagnosisPromptSystemMessage = "",
            dreamDiagnosisPromptUserMessage1 = "",
            dreamDiagnosisPromptUserMessage2 = "",
            isSubscripting = false,
            withinDreamDiagnosisLimit = false,
            launchModalFrequency = 0,
            launchModalTitleText = "",
            launchModalImageURL = "",
            launchModalBodyText = "",
            launchModalButtonText = "",
            launchModalButtonURL = ""
        )
    }
}