package tianqi.kmp.practice.theme

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight


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

@Composable
internal fun font(name: String, weight: FontWeight, style: FontStyle): Font {
    val context = LocalContext.current
    val id = context.resources.getIdentifier(name, "font", context.packageName)
    return Font(id, weight, style)
}