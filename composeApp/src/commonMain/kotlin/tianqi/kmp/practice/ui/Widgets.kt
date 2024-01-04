package tianqi.kmp.practice.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppBar(
    title: String,
    navigationIcon: @Composable () -> Unit = {},
    isDarkMode: Boolean,
    onSwitchCheckedChange: (Boolean) -> Unit
) {
    CenterAlignedTopAppBar(
        title = {
            Text(
                text = title,
                style = MaterialTheme.typography.displayMedium
            )
        },
        navigationIcon = navigationIcon,
        actions = {
            Switch(
                checked = isDarkMode,
                onCheckedChange = {
                    onSwitchCheckedChange(it)
                }
            )
        }

    )
}

@Composable
fun BackNavigation(onBackClicked: () -> Unit) {
    IconButton(onClick = { onBackClicked() }) {
        Icon(
            imageVector = Icons.Filled.ArrowBack,
            contentDescription = "Localized description"
        )
    }
}

@Composable
fun LaunchTag(isSuccess: Boolean, modifier: Modifier) {
    val color = if (isSuccess) Color.Green else Color.Red
    val text = if (isSuccess) "Launch Success" else "Launch Failed"
    Text(
        text = text,
        style = MaterialTheme.typography.labelSmall,
        color = Color.White,
        modifier = modifier.background(color = color, shape = RoundedCornerShape(4.dp))
            .padding(2.dp)
    )
}