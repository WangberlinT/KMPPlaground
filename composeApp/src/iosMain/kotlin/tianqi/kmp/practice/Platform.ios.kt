package tianqi.kmp.practice

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.toComposeImageBitmap
import kotlinx.coroutines.runBlocking
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.resource
import org.jetbrains.skia.Image
import platform.UIKit.UIDevice

class IOSPlatform : Platform {
    override val name: String =
        UIDevice.currentDevice.systemName() + " " + UIDevice.currentDevice.systemVersion
}

actual fun getPlatform(): Platform = IOSPlatform()

@OptIn(ExperimentalResourceApi::class)
@Composable
actual fun getImageAsset(filename: String): ImageBitmap {
    val byteArray = runBlocking {
        resource("drawable/$filename").readBytes()
    }
    return Image.makeFromEncoded(byteArray).toComposeImageBitmap()
}