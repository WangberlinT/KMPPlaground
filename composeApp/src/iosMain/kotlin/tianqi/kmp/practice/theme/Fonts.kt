package tianqi.kmp.practice.theme

import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.platform.Font
import kotlinx.coroutines.runBlocking
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.resource


@Composable
actual fun abrilFatface() = FontFamily(
    font("abrilfatface_regular", FontWeight.Normal, FontStyle.Normal)
)

@Composable
actual fun montserrat() = FontFamily(
    font("montserrat_regular", FontWeight.Normal, FontStyle.Normal),
    font("montserrat_regular", FontWeight.Normal, FontStyle.Normal),
    font("montserrat_bold", FontWeight.Bold, FontStyle.Normal)
)

private val cache: MutableMap<String, Font> = mutableMapOf()

@OptIn(ExperimentalResourceApi::class)
private fun font(res: String, weight: FontWeight, style: FontStyle): Font {
    return cache.getOrPut(res) {
        val byteArray = runBlocking {
            resource("font/$res.ttf").readBytes()
        }
        Font(res, byteArray, weight, style)
    }
}

