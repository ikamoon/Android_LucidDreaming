package com.aura_weavers.luciddreaming.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.aura_weavers.luciddreaming.data.TaskCardData

@Composable
fun TaskCard(
    taskData: TaskCardData,
    onClick: () -> Unit = {},
    modifier: Modifier = Modifier
) {
    val backgroundColor = if (taskData.isEnabled) {
        Color.White
    } else {
        Color.Gray.copy(alpha = 0.3f)
    }

    val contentColor = if (taskData.isEnabled) {
        Color.Black
    } else {
        Color.Gray
    }

    Card(
        modifier = modifier
            .fillMaxWidth()
            .clickable(enabled = taskData.isEnabled) { onClick() },
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = backgroundColor),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Icon
//            Box(
//                modifier = Modifier
//                    .size(48.dp)
//                    .background(
//                        color = getTypeColor(taskData.type).copy(alpha = 0.1f),
//                        shape = RoundedCornerShape(8.dp)
//                    ),
//                contentAlignment = Alignment.Center
//            ) {
//                Icon(
//                    imageVector = getIconForType(taskData.type),
//                    contentDescription = null,
//                    tint = getTypeColor(taskData.type),
//                    modifier = Modifier.size(24.dp)
//                )
//            }
//
//            Spacer(modifier = Modifier.width(16.dp))

            // Content
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = taskData.title,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = contentColor,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )

                if (taskData.subtitle.isNotEmpty()) {
                    Text(
                        text = taskData.subtitle,
                        fontSize = 14.sp,
                        color = contentColor.copy(alpha = 0.7f),
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }

                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = taskData.duration,
                        fontSize = 12.sp,
                        color = contentColor.copy(alpha = 0.6f)
                    )

                    Spacer(modifier = Modifier.width(8.dp))

                    Box(
                        modifier = Modifier
                            .background(
                                color = getTypeColor(taskData.type).copy(alpha = 0.2f),
                                shape = RoundedCornerShape(4.dp)
                            )
                            .padding(horizontal = 8.dp, vertical = 2.dp)
                    ) {
                        Text(
                            text = taskData.type,
                            fontSize = 10.sp,
                            color = getTypeColor(taskData.type),
                            fontWeight = FontWeight.Medium
                        )
                    }
                }
            }

            // Share button or arrow
            if (taskData.hasShareButton) {
                IconButton(
                    onClick = { /* Handle share */ }
                ) {
                    Icon(
                        imageVector = Icons.Default.Share,
                        contentDescription = "Share",
                        tint = contentColor.copy(alpha = 0.6f)
                    )
                }
            } else {
                Icon(
                    imageVector = Icons.Default.KeyboardArrowRight,
                    contentDescription = null,
                    tint = contentColor.copy(alpha = 0.4f),
                    modifier = Modifier.size(20.dp)
                )
            }
        }
    }
}

@Composable
private fun getIconForType(type: String): ImageVector {
    return when (type) {
        "日記" -> Icons.Default.Edit
        "コラム" -> Icons.Default.Menu
        "動画" -> Icons.Default.PlayArrow
        "アクティビティ" -> Icons.Default.Menu
        else -> Icons.Default.Menu
    }
}

@Composable
private fun getTypeColor(type: String): Color {
    return when (type) {
        "日記" -> Color(0xFF4CAF50) // Green
        "コラム" -> Color(0xFF2196F3) // Blue
        "動画" -> Color(0xFF9C27B0) // Purple
        "アクティビティ" -> Color(0xFF607D8B) // Blue Grey
        else -> Color(0xFF757575) // Grey
    }
}
//
//@Preview(showBackground = true)
//@Composable
//fun TaskCardPreview() {
//    LucidDreamingAppTheme{
//        Column(
//            modifier = Modifier.padding(16.dp),
////            verticalArrangement = Arrangement.spacing(16.dp)
//        ) {
//            TaskCard(
//                taskData = TaskCardData(
//                    index = 0,
//                    title = "夢日記を書く",
//                    subtitle = "",
//                    duration = "5分",
//                    type = "日記",
//                    icon = "edit",
//                    hasShareButton = true
//                )
//            )
//
//            TaskCard(
//                taskData = TaskCardData(
//                    index = 1,
//                    title = "願いを叶えるヒントを得る",
//                    subtitle = "「願望実現の鍵」",
//                    duration = "2分",
//                    type = "コラム",
//                    icon = "book",
//                    hasShareButton = false
//                )
//            )
//
//            TaskCard(
//                taskData = TaskCardData(
//                    index = 2,
//                    title = "よく寝る",
//                    subtitle = "良い睡眠は夢を叶える第一歩",
//                    duration = "7時間",
//                    type = "アクティビティ",
//                    icon = "bedtime",
//                    isEnabled = false
//                )
//            )
//        }
//    }
//}