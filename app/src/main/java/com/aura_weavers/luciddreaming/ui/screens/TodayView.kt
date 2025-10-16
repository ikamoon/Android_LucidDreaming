package com.aura_weavers.luciddreaming.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.aura_weavers.luciddreaming.ui.theme.LucidDreamingTheme

@Composable
fun TodayView(
    modifier: Modifier = Modifier,
    onNavigateToTimer: () -> Unit = {},
    contentPadding: PaddingValues = PaddingValues(horizontal = 16.dp, vertical = 30.dp),
) {
    Column(
        modifier = modifier.padding(contentPadding)
    ) {
        HeaderSection(
//        timeOfDay = viewModel.getTimeOfDay(),
            onNavigateToTimer = onNavigateToTimer
        )

        Text(
            text = "Hello Android!",
            modifier = Modifier
        )
    }
}

@Composable
private fun HeaderSection(
    onNavigateToTimer: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column {
            Text(
                text = "Good morning", //$timeOfDay",
                fontSize = 14.sp,
                color = Color.Gray
            )
            Text(
                text = "夢を叶える準備をする",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF4A4A4A)
            )
        }

        IconButton(onClick = onNavigateToTimer) {
            Icon(
                imageVector = Icons.Default.Notifications,
                contentDescription = "Timer",
                tint = Color(0xFF9C27B0),
                modifier = Modifier.size(24.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TodayViewPreview() {
    LucidDreamingTheme {
        TodayView()
    }
}
