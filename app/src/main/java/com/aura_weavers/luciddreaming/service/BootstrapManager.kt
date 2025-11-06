package com.aura_weavers.luciddreaming.service

import com.aura_weavers.luciddreaming.model.Column
import com.aura_weavers.luciddreaming.model.DreamInductionVideo
import com.aura_weavers.luciddreaming.model.GetBootstrapResponse
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

object BootstrapManager {
    private val _todayColumn = MutableStateFlow<Column?>(null)
    val todayColumn: StateFlow<Column?> = _todayColumn

    private val _todayDreamInduction = MutableStateFlow<DreamInductionVideo?>(null)
    val todayDreamInduction: StateFlow<DreamInductionVideo?> = _todayDreamInduction

    private val _lineBannerImageURL = MutableStateFlow("")
    val lineBannerImageURL: StateFlow<String> = _lineBannerImageURL

    private val _lineURL = MutableStateFlow("")
    val lineURL: StateFlow<String> = _lineURL

    private val _touURL = MutableStateFlow("")
    val touURL: StateFlow<String> = _touURL

    private val _ppURL = MutableStateFlow("")
    val ppURL: StateFlow<String> = _ppURL

    private val _dreamDiagnosisPromptSystemMessage = MutableStateFlow("")
    val dreamDiagnosisPromptSystemMessage: StateFlow<String> = _dreamDiagnosisPromptSystemMessage

    private val _dreamDiagnosisPromptUserMessage1 = MutableStateFlow("")
    val dreamDiagnosisPromptUserMessage1: StateFlow<String> = _dreamDiagnosisPromptUserMessage1

    private val _dreamDiagnosisPromptUserMessage2 = MutableStateFlow("")
    val dreamDiagnosisPromptUserMessage2: StateFlow<String> = _dreamDiagnosisPromptUserMessage2

    private val _isSubscripting = MutableStateFlow(false)
    val isSubscripting: StateFlow<Boolean> = _isSubscripting

    private val _withinDreamDiagnosisLimit = MutableStateFlow(false)
    val withinDreamDiagnosisLimit: StateFlow<Boolean> = _withinDreamDiagnosisLimit

    private val _launchModalFrequency = MutableStateFlow(0)
    val launchModalFrequency: StateFlow<Int> = _launchModalFrequency

    private val _launchModalTitleText = MutableStateFlow("")
    val launchModalTitleText: StateFlow<String> = _launchModalTitleText

    private val _launchModalImageURL = MutableStateFlow("")
    val launchModalImageURL: StateFlow<String> = _launchModalImageURL

    private val _launchModalBodyText = MutableStateFlow("")
    val launchModalBodyText: StateFlow<String> = _launchModalBodyText

    private val _launchModalButtonText = MutableStateFlow("")
    val launchModalButtonText: StateFlow<String> = _launchModalButtonText

    private val _launchModalButtonURL = MutableStateFlow("")
    val launchModalButtonURL: StateFlow<String> = _launchModalButtonURL

    fun setTodayData(bootstrap: GetBootstrapResponse) {
        _todayColumn.value = bootstrap.column
        _todayDreamInduction.value = bootstrap.video
        _lineBannerImageURL.value = bootstrap.lineBannerImageURL
        _lineURL.value = bootstrap.lineURL
        _touURL.value = bootstrap.touURL
        _ppURL.value = bootstrap.ppURL
        _dreamDiagnosisPromptSystemMessage.value = bootstrap.dreamDiagnosisPromptSystemMessage
        _dreamDiagnosisPromptUserMessage1.value = bootstrap.dreamDiagnosisPromptUserMessage1
        _dreamDiagnosisPromptUserMessage2.value = bootstrap.dreamDiagnosisPromptUserMessage2
        _isSubscripting.value = bootstrap.isSubscripting
        _withinDreamDiagnosisLimit.value = bootstrap.withinDreamDiagnosisLimit
        _launchModalFrequency.value = bootstrap.launchModalFrequency ?: 0
        _launchModalTitleText.value = bootstrap.launchModalTitleText ?: ""
        _launchModalImageURL.value = bootstrap.launchModalImageURL ?: ""
        _launchModalBodyText.value = bootstrap.launchModalBodyText ?: ""
        _launchModalButtonText.value = bootstrap.launchModalButtonText ?: ""
        _launchModalButtonURL.value = bootstrap.launchModalButtonURL ?: ""
    }

    suspend fun updateData() {
        val response = SupabaseManager.fetchBootstrap()
        setTodayData(bootstrap = response)
    }
}