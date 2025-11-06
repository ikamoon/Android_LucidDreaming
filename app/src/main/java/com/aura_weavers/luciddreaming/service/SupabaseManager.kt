package com.aura_weavers.luciddreaming.service

import com.aura_weavers.luciddreaming.model.GetBootstrapResponse
import com.example.luciddreamingapp.data.Column
import com.example.luciddreamingapp.data.DreamInductionVideo

object SupabaseManager {
    suspend fun fetchBootstrap(): GetBootstrapResponse {
        // TODO: Implement actual fetching logic from Supabase
        return GetBootstrapResponse(
            column = Column("", title = "", content = "", author = "", publishDate = "", readTime = 0),
            video = DreamInductionVideo("", title = "", description = "", videoUrl = "", thumbnailUrl = "", duration = 0),
            lineBannerImageURL = "https://firebasestorage.googleapis.com/v0/b/dreammagic-21768/o/line_bonus_banner_money.png?alt=media&token=726e34ae-fcdd-4228-a9c3-4c87fb4a52dd",
            lineURL = "https://lin.ee/fIj3TeW",
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