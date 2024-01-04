package tianqi.kmp.practice.theme

import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp


@Composable
fun AppTypography(): Typography {
    return Typography(
        displayLarge = TextStyle(
            fontFamily = abrilFatface(),
            fontWeight = FontWeight.Normal,
            fontSize = 36.sp
        ),
        displayMedium = TextStyle(
            fontFamily = abrilFatface(),
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp
        ),
        labelSmall = TextStyle(
            fontFamily = montserrat(),
            fontWeight = FontWeight.Bold,
            fontSize = 10.sp
        ),
        bodyLarge = TextStyle(
            fontFamily = montserrat(),
            fontWeight = FontWeight.Normal,
            fontSize = 14.sp
        )
    )
}