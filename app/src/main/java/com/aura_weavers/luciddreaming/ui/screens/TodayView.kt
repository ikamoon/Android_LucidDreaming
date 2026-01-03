package com.aura_weavers.luciddreaming.ui.screens

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.Image
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.SubcomposeAsyncImage
import com.aura_weavers.luciddreaming.data.TaskCardData
import com.aura_weavers.luciddreaming.ui.components.TaskCard
import com.aura_weavers.luciddreaming.ui.theme.LucidDreamingTheme
import com.aura_weavers.luciddreaming.viewmodel.TodayViewModel
import com.aura_weavers.luciddreaming.model.Column
import com.aura_weavers.luciddreaming.model.DreamInductionVideo

@Composable
fun TodayView(
    modifier: Modifier = Modifier,
    viewModel: TodayViewModel = TodayViewModel(),
    onNavigateToDreamDiary: () -> Unit = {},
    onNavigateToBookshelf: (Column) -> Unit = {},
    onPlayVideo: (DreamInductionVideo) -> Unit = {},
    onNavigateToTimer: () -> Unit = {},
    contentPadding: PaddingValues = PaddingValues(horizontal = 16.dp, vertical = 30.dp),
) {
    val isLoading by viewModel.isLoading.collectAsStateWithLifecycle()
    val error by viewModel.error.collectAsStateWithLifecycle()
    val todayColumn by viewModel.todayColumn.collectAsStateWithLifecycle()
    val todayDreamInduction by viewModel.todayDreamInduction.collectAsStateWithLifecycle()
    val showPaywall by viewModel.showPaywall.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.loadBootstrapIfNeeded()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF5F5F5))
    ) {
        if (isLoading) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        } else if (error != null) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "エラーが発生しました: $error",
                    color = Color.Red,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(16.dp)
                )
            }
        } else {
            LazyColumn(
                modifier = modifier.padding(contentPadding)
            ) {
                item {
                    HeaderSection(
                        viewModel = viewModel,
                        onNavigateToTimer = onNavigateToTimer
                    )
                }

                item {
                    Spacer(modifier = Modifier.height(32.dp))
                }

                item {
                    val context = LocalContext.current
                    LineBannerSection(
                        bannerImageUrl = viewModel.lineBannerImageURL.value,
                        onNavigateToLine = {
                            val urlString = viewModel.lineURL.value
                            val intent =
                                Intent(Intent.ACTION_VIEW, Uri.parse(urlString))
                            context.startActivity(intent)
                        }
                    )
                }

                item {
                    Spacer(modifier = Modifier.height(32.dp))
                }

                // Today's Task List Section
                item {
                    TaskListSection(
                        todayColumn = todayColumn,
                        todayDreamInduction = todayDreamInduction,
                        onNavigateToDreamDiary = onNavigateToDreamDiary,
                        onNavigateToBookshelf = onNavigateToBookshelf,
                        onPlayVideo = onPlayVideo,
                        onShowPaywall = { viewModel.showPaywall }
                    )
                }
            }
        }
    }
}

@Composable
private fun HeaderSection(
    viewModel: TodayViewModel,
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
                text = "Good ${viewModel.getTimeOfDay()}",
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

//        IconButton(onClick = onNavigateToTimer) {
//            Icon(
//                imageVector = Icons.Default.Notifications,
//                contentDescription = "Timer",
//                tint = Color(0xFF9C27B0),
//                modifier = Modifier.size(24.dp)
//            )
//        }
    }
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
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        )
    ) {
        Column (
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(26.dp) //spacing(16.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
//                Icon(
//                    imageVector = Icons.Default.Menu,
//                    contentDescription = null,
//                    tint = Color(0xFF9C27B0),
//                    modifier = Modifier.size(20.dp)
//                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "寝ながら夢を叶える体質をサポート",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF4A4A4A)
                )
            }

            SubcomposeAsyncImage(
                model = bannerImageUrl,
                contentDescription = "LINE Banner",
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(8.dp)),
                success = {
                    val size = painter.intrinsicSize
                    val ratio = if (size.width > 0f && size.height > 0f) size.width / size.height else 1f
                    Image(
                        painter = painter,
                        contentDescription = "LINE Banner",
                        modifier = Modifier
                            .fillMaxWidth()
                            .aspectRatio(ratio)
                            .clip(RoundedCornerShape(8.dp)),
                        contentScale = ContentScale.Fit
                    )
                }
            )

            Button(
                onClick = onNavigateToLine,
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF4CAF50)
                ),
                shape = RoundedCornerShape(12.dp)
            ) {
//                Icon(
//                    imageVector = Icons.Default.Menu,
//                    contentDescription = null,
//                    modifier = Modifier.size(20.dp)
//                )
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

@Composable
private fun TaskListSection(
    todayColumn: com.aura_weavers.luciddreaming.model.Column?,
    todayDreamInduction: com.aura_weavers.luciddreaming.model.DreamInductionVideo?,
    onNavigateToDreamDiary: () -> Unit,
    onNavigateToBookshelf: (Column) -> Unit,
    onPlayVideo: (DreamInductionVideo) -> Unit,
    onShowPaywall: () -> Unit
) {
    Column(
        modifier = Modifier.padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp) //spacing(16.dp)
    ) {
        // Morning Tasks
//        TaskSectionHeader(
//            icon = Icons.Default.Settings,
//            title = "朝起きたらやること",
//            iconTint = Color(0xFFFF9800)
//        )
//
//        TaskCard(
//            taskData = TaskCardData(
//                index = 3,
//                title = "夢日記を書く",
//                subtitle = "",
//                duration = "5分",
//                type = "日記",
//                icon = "edit",
//                hasShareButton = true
//            ),
//            onClick = onNavigateToDreamDiary
//        )
//
//        Spacer(modifier = Modifier.height(8.dp))

        // Night Tasks
        TaskSectionHeader(
            icon = Icons.Default.Star,
            title = "夜寝る前にやること",
            iconTint = Color(0xFF2196F3)
        )

        TaskCard(
            taskData = TaskCardData(
                index = 0,
                title = "願いを叶えるヒントを得る",
                subtitle = "「${todayColumn?.title ?: ""}」",
                duration = "2分",
                type = "コラム",
                icon = "book",
                hasShareButton = false
            ),
            onClick = {
                todayColumn?.let(onNavigateToBookshelf)
            }
        )

        TaskCard(
            taskData = TaskCardData(
                index = 1,
                title = "睡眠導入とアファメーション",
                subtitle = "「${todayDreamInduction?.title ?: "明晰夢への導入を聞く"}」",
                duration = "5分",
                type = "動画",
                icon = "play",
                hasShareButton = false
            ),
            onClick = {
                todayDreamInduction?.let { video ->
                    onPlayVideo(video)
                }
            }
        )

//        TaskCard(
//            taskData = TaskCardData(
//                index = 2,
//                title = "よく寝る",
//                subtitle = "良い睡眠は夢を叶える第一歩",
//                duration = "7時間",
//                type = "アクティビティ",
//                icon = "bedtime",
//                isEnabled = false
//            )
//        )
    }
}


@Composable
private fun TaskSectionHeader(
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    title: String,
    iconTint: Color
) {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = iconTint,
            modifier = Modifier.size(16.dp)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = title,
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF4A4A4A)
        )
    }
}