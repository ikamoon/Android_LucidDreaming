package com.aura_weavers.luciddreaming.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aura_weavers.luciddreaming.service.BootstrapManager
import com.aura_weavers.luciddreaming.model.Column
import com.aura_weavers.luciddreaming.model.DreamInductionVideo
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.util.Calendar

// 仮のオブジェクト
object SubscriptionManager {
    val shared = this
    var isSubscribed = false
}

object LaunchModalManager {
    val shared = this
    private var launchCount = 0

    fun incrementLaunchCount() {
        launchCount++
    }

    fun shouldShowPromo(frequency: Int): Boolean {
        if (frequency <= 0) return false
        return launchCount % frequency == 0
    }
}

class TodayViewModel : ViewModel() {
    private var hasLoadedBootstrap = false
    private val _selectedTask = MutableStateFlow<Int?>(null)
    val selectedTask: StateFlow<Int?> = _selectedTask.asStateFlow()

    private val _showProfile = MutableStateFlow(false)
    val showProfile: StateFlow<Boolean> = _showProfile.asStateFlow()

    private val _showPaywall = MutableStateFlow(false)
    val showPaywall: StateFlow<Boolean> = _showPaywall.asStateFlow()

    private val _showColumn = MutableStateFlow(false)
    val showColumn: StateFlow<Boolean> = _showColumn.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    private val _error = MutableStateFlow<Throwable?>(null)
    val error: StateFlow<Throwable?> = _error.asStateFlow()

    private val _todayColumn = MutableStateFlow<Column?>(null)
    val todayColumn: StateFlow<Column?> = _todayColumn.asStateFlow()

    private val _todayDreamInduction = MutableStateFlow<DreamInductionVideo?>(null)
    val todayDreamInduction: StateFlow<DreamInductionVideo?> = _todayDreamInduction.asStateFlow()

    private val _lineBannerImageURL = MutableStateFlow("")
    val lineBannerImageURL: StateFlow<String> = _lineBannerImageURL.asStateFlow()

    private val _lineURL = MutableStateFlow("")
    val lineURL: StateFlow<String> = _lineURL.asStateFlow()

    private val _launchModalFrequency = MutableStateFlow(0)
    val launchModalFrequency: StateFlow<Int> = _launchModalFrequency.asStateFlow()

    init {
        viewModelScope.launch {
            BootstrapManager.todayColumn.collect { _todayColumn.value = it }
        }
        viewModelScope.launch {
            BootstrapManager.todayDreamInduction.collect { _todayDreamInduction.value = it }
        }
        viewModelScope.launch {
            BootstrapManager.lineBannerImageURL.collect { _lineBannerImageURL.value = it }
        }
        viewModelScope.launch {
            BootstrapManager.lineURL.collect { _lineURL.value = it }
        }
        viewModelScope.launch {
            BootstrapManager.launchModalFrequency.collect { _launchModalFrequency.value = it }
        }
    }

    fun getTimeOfDay(): String {
        val hour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY)
        return when (hour) {
            in 5..11 -> "Morning"
            in 12..17 -> "Afternoon"
            in 18..21 -> "Evening"
            else -> "Night"
        }
    }

    fun loadBootstrapIfNeeded() {
        if (_isLoading.value || hasLoadedBootstrap) return
        val needsData = todayColumn.value == null || todayDreamInduction.value == null
        if (!needsData && _error.value == null) {
            hasLoadedBootstrap = true
            return
        }
        loadBootstrap()
    }

    fun loadBootstrap() {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                BootstrapManager.updateData()
                _error.value = null
                hasLoadedBootstrap = true
            } catch (e: Exception) {
                _error.value = e
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun showPaywall() {
        _showPaywall.value = true
    }

    fun showNextTransitionByColumn() {
        val column = todayColumn.value
        if (column != null) {
            // isPremiumプロパティをColumnに追加する必要がある
            // if (column.isPremium && !SubscriptionManager.shared.isSubscribed) {
            //     _showPaywall.value = true
            // } else {
            _showColumn.value = true
            // }
        }
    }

    fun shouldShowPaywallByVideo(): Boolean {
        val video = todayDreamInduction.value
        return if (video != null) {
            // isPremiumプロパティをDreamInductionVideoに追加する必要がある
            // video.isPremium && !SubscriptionManager.shared.isSubscribed
            false
        } else {
            false
        }
    }

    fun incrementLaunchCountAndCheckShouldShowLaunchModal(): Boolean {
        LaunchModalManager.shared.incrementLaunchCount()
        if (launchModalFrequency.value < 1) {
            return false
        }
        if (LaunchModalManager.shared.shouldShowPromo(launchModalFrequency.value)) {
            return true
        }
        return false
    }
}