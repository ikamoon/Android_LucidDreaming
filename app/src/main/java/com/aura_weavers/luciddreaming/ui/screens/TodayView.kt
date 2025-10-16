package com.aura_weavers.luciddreaming.ui.screens

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.aura_weavers.luciddreaming.ui.theme.LucidDreamingTheme

@Composable
fun TodayView(modifier: Modifier = Modifier) {
    Text(
        text = "Hello Android!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun TodayViewPreview() {
    LucidDreamingTheme {
        TodayView()
    }
}
