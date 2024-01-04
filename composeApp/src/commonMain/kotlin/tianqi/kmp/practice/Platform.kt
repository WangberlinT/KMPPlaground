package tianqi.kmp.practice

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.ImageBitmap

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform

@Composable
expect fun getImageAsset(filename: String): ImageBitmap