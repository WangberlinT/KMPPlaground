package tianqi.kmp.practice

import android.os.Build
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.imageResource

class AndroidPlatform : Platform {
    override val name: String = "Android ${Build.VERSION.SDK_INT}"
}

actual fun getPlatform(): Platform = AndroidPlatform()

@Composable
actual fun getImageAsset(filename: String): ImageBitmap {
    val context = LocalContext.current
    val name = filename.substringBefore(".")
    val id = context.resources.getIdentifier(name, "drawable", context.packageName)
    return ImageBitmap.imageResource(id)
}