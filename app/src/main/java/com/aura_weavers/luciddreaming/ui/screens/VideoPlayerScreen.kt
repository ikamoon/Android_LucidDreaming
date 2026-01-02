package com.aura_weavers.luciddreaming.ui.screens

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import androidx.media3.common.MediaItem
import androidx.media3.common.Player
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.ui.PlayerView

private const val TAG = "VideoPlayerScreen"

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun VideoPlayerScreen(
    videoUrl: String,
    title: String? = null,
    onClose: () -> Unit = {}
) {
    val context = LocalContext.current

    val exoPlayer = remember(videoUrl) {
        if (videoUrl.isNotEmpty()) {
            try {
                ExoPlayer.Builder(context).build().apply {
                    repeatMode = Player.REPEAT_MODE_OFF
                }
            } catch (e: Exception) {
                Log.e(TAG, "Error creating ExoPlayer", e)
                null
            }
        } else {
            null
        }
    }

    LaunchedEffect(exoPlayer, videoUrl) {
        if (exoPlayer != null && videoUrl.isNotEmpty()) {
            try {
                val mediaItem = MediaItem.fromUri(videoUrl)
                exoPlayer.setMediaItem(mediaItem)
                exoPlayer.prepare()
                exoPlayer.playWhenReady = true
            } catch (e: Exception) {
                Log.e(TAG, "Error loading media: $videoUrl", e)
            }
        }
    }

    DisposableEffect(exoPlayer) {
        onDispose {
            try {
                exoPlayer?.let { player ->
                    player.stop()
                    player.clearMediaItems()
                    player.release()
                }
            } catch (e: Exception) {
                Log.e(TAG, "Error releasing ExoPlayer", e)
            }
        }
    }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {},
                navigationIcon = {
                    IconButton(onClick = onClose) {
                        Icon(imageVector = Icons.Default.Close, contentDescription = "Close")
                    }
                }
            )
        }
    ) { innerPadding ->
        if (exoPlayer != null) {
            AndroidView(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding),
                factory = { ctx ->
                    try {
                        PlayerView(ctx).apply {
                            player = exoPlayer
                            useController = true
                            controllerAutoShow = true
                        }
                    } catch (e: Exception) {
                        Log.e(TAG, "Error creating PlayerView", e)
                        PlayerView(ctx)
                    }
                },
                update = { playerView ->
                    try {
                        if (playerView.player != exoPlayer) {
                            playerView.player = exoPlayer
                        }
                    } catch (e: Exception) {
                        Log.e(TAG, "Error updating PlayerView", e)
                    }
                }
            )
        } else {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding),
                contentAlignment = Alignment.Center
            ) {
                Text("ビデオプレイヤーの初期化に失敗しました")
            }
        }
    }
}

