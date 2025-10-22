package com.aura_weavers.luciddreaming.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.aura_weavers.luciddreaming.ui.theme.LucidDreamingTheme
import com.aura_weavers.luciddreaming.viewmodel.TodayViewModel

@Composable
fun TodayView(
    modifier: Modifier = Modifier,
    viewModel: TodayViewModel = TodayViewModel(),
    onNavigateToTimer: () -> Unit = {},
    contentPadding: PaddingValues = PaddingValues(horizontal = 16.dp, vertical = 30.dp),
) {
    Column(
        modifier = modifier.padding(contentPadding)
    ) {
        HeaderSection(
            timeOfDay = viewModel.getTimeOfDay(),
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
    timeOfDay: String = "day",
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
                text = "Good $timeOfDay",
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

    LineBannerSection(
        bannerImageUrl = "https://nkzxjrsmqrrowyuzbapl.supabase.co/storage/v1/object/public/banners/line_bonus_banner.jpg", //viewModel.lineBannerImageURL,
        onNavigateToLine = {} //onNavigateToLine
    )
}

@Preview(showBackground = true)
@Composable
fun TodayViewPreview() {
    LucidDreamingTheme {
        TodayView()
    }
}

@Composable
private fun LineBannerSection(
    bannerImageUrl: String,
    onNavigateToLine: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column (
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(26.dp) //spacing(16.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Default.Menu,
                    contentDescription = null,
                    tint = Color(0xFF9C27B0),
                    modifier = Modifier.size(20.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "寝ながら夢を叶える体質をサポート",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF4A4A4A)
                )
            }

            AsyncImage(
                model = bannerImageUrl,
                contentDescription = "LINE Banner",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .clip(RoundedCornerShape(8.dp)),
                contentScale = ContentScale.Crop
            )

            Button(
                onClick = onNavigateToLine,
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF4CAF50)
                ),
                shape = RoundedCornerShape(12.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Menu,
                    contentDescription = null,
                    modifier = Modifier.size(20.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "試してみる",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold
                )
            }
        }
    }
}