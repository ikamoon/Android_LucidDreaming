package com.aura_weavers.luciddreaming.viewmodel

import androidx.lifecycle.ViewModel
import java.util.Calendar

class TodayViewModel : ViewModel() {

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
}