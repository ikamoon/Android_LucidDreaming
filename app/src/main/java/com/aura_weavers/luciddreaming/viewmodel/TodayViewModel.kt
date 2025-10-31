package com.aura_weavers.luciddreaming.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.luciddreamingapp.data.Column
import com.example.luciddreamingapp.data.DreamInductionVideo
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.util.Calendar

class TodayViewModel : ViewModel() {
    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error.asStateFlow()


    private val _todayColumn = MutableStateFlow<Column?>(null)
    val todayColumn: StateFlow<Column?> = _todayColumn.asStateFlow()

    private val _todayDreamInduction = MutableStateFlow<DreamInductionVideo?>(null)
    val todayDreamInduction: StateFlow<DreamInductionVideo?> = _todayDreamInduction.asStateFlow()

    private val _showPaywall = MutableStateFlow(false)
    val showPaywall: StateFlow<Boolean> = _showPaywall.asStateFlow()

    val lineBannerImageURL = "https://firebasestorage.googleapis.com/v0/b/dreammagic-21768/o/line_bonus_banner_money.png?alt=media&token=726e34ae-fcdd-4228-a9c3-4c87fb4a52dd"
    val lineURL = "https://lin.ee/fIj3TeW"

    init {
        loadBootstrapIfNeeded()
    }

    fun getTimeOfDay(): String {
        val calendar = Calendar.getInstance()
        val hour = calendar.get(Calendar.HOUR_OF_DAY)
        return when (hour) {
            in 5..11 -> "Morning"
            in 12..17 -> "Afternoon"
            in 18..21 -> "Evening"
            else -> "Night"
        }
    }

    fun shouldShowPaywall(): Boolean {
        // Mock implementation - in real app, check user subscription status
        return false
    }

    fun loadBootstrapIfNeeded() {
        viewModelScope.launch {
            loadBootstrap()
        }
    }

    suspend fun loadBootstrap() {
        _isLoading.value = true
        _error.value = null

        try {
            // Simulate API calls
            kotlinx.coroutines.delay(1000)

            // Mock data
            _todayColumn.value = Column(
                id = "1",
                title = "願望実現の鍵",
                content = "夢を叶えるための具体的な方法について...",
                author = "夢の専門家",
                publishDate = "2024-01-15",
                readTime = 2
            )

            _todayDreamInduction.value = DreamInductionVideo(
                id = "1",
                title = "明晰夢への導入を聞く",
                description = "リラックスして眠りにつくための音声ガイド",
                videoUrl = "https://example.com/video.mp4",
                thumbnailUrl = "https://via.placeholder.com/300x200/9C27B0/FFFFFF?text=Sleep+Induction",
                duration = 300
            )

        } catch (e: Exception) {
            _error.value = e.message ?: "Unknown error occurred"
        } finally {
            _isLoading.value = false
        }
    }
}