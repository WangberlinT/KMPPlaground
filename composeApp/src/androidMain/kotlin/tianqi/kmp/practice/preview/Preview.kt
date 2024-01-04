package tianqi.kmp.practice.preview

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import tianqi.kmp.practice.model.RocketLaunch
import tianqi.kmp.practice.model.fakeLaunches
import tianqi.kmp.practice.theme.AppTheme
import tianqi.kmp.practice.ui.screens.DetailScreenUi
import tianqi.kmp.practice.ui.screens.HomeScreenUi


@Composable
@Preview
fun PreviewHomeScreenUi() {
    AppTheme(isDarkTheme = true) {
        HomeScreenUi(
            list = RocketLaunch.fakeLaunches()
        )
    }
}

@Composable
@Preview
fun PreviewDetailScreenUi() {
    AppTheme(isDarkTheme = false) {
        DetailScreenUi(
            rocketLaunch = RocketLaunch.fakeLaunches().first()
        )
    }
}