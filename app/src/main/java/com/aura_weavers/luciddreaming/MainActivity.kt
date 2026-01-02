package com.aura_weavers.luciddreaming

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteScaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewScreenSizes
import com.aura_weavers.luciddreaming.ui.screens.TodayView
import com.aura_weavers.luciddreaming.ui.screens.SecureWebViewScreen
import com.aura_weavers.luciddreaming.ui.theme.LucidDreamingTheme
import com.aura_weavers.luciddreaming.model.Column

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LucidDreamingTheme {
                LucidDreamingApp()
            }
        }
    }
}

@PreviewScreenSizes
@Composable
fun LucidDreamingApp() {
    var currentDestination by rememberSaveable { mutableStateOf(AppDestinations.HOME) }
    var activeColumnForWebView by rememberSaveable { mutableStateOf<com.aura_weavers.luciddreaming.model.Column?>(null) }

    NavigationSuiteScaffold(
        navigationSuiteItems = {
            AppDestinations.entries.forEach {
                item(
                    icon = {
                        Icon(
                            it.icon,
                            contentDescription = it.label
                        )
                    },
                    label = { Text(it.label) },
                    selected = it == currentDestination,
                    onClick = { currentDestination = it }
                )
            }
        }
    ) {
        val context = LocalContext.current

        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
            if (activeColumnForWebView != null) {
                val column = activeColumnForWebView!!
                SecureWebViewScreen(
                    url = column.contentUrl,
                    columnId = column.id,
                    onClose = { activeColumnForWebView = null }
                )
            } else {
                when (currentDestination) {
                    AppDestinations.HOME -> TodayView(
                        modifier = Modifier.padding(innerPadding),
                        onNavigateToTimer = { },
                        onNavigateToBookshelf = { column -> activeColumnForWebView = column },
                        onPlayVideo = { dreamInductionVideo ->
                            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(dreamInductionVideo.videoUrl))
                            context.startActivity(intent)
                        }
                    )
                    AppDestinations.FAVORITES -> Text(
                        text = "Favorites",
                        modifier = Modifier.padding(innerPadding)
                    )
                    AppDestinations.PROFILE -> Text(
                        text = "Profile",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

enum class AppDestinations(
    val label: String,
    val icon: ImageVector,
) {
    HOME("Today", Icons.Default.Home),
    FAVORITES("Favorites", Icons.Default.Favorite),
    PROFILE("Profile", Icons.Default.AccountBox),
}
