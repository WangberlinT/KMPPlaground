package tianqi.kmp.practice

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.arkivanov.decompose.extensions.compose.jetbrains.stack.Children
import com.arkivanov.decompose.extensions.compose.jetbrains.stack.animation.slide
import com.arkivanov.decompose.extensions.compose.jetbrains.stack.animation.stackAnimation
import com.arkivanov.decompose.extensions.compose.jetbrains.subscribeAsState
import com.arkivanov.decompose.router.stack.pop
import tianqi.kmp.practice.components.Child
import tianqi.kmp.practice.components.RootComponent
import tianqi.kmp.practice.theme.AppTheme
import tianqi.kmp.practice.ui.AppBar
import tianqi.kmp.practice.ui.BackNavigation
import tianqi.kmp.practice.ui.screens.DetailScreen
import tianqi.kmp.practice.ui.screens.HomeScreen


@Composable
fun App(root: RootComponent) {
    root.onSystemDarkModeChanged(isSystemInDarkTheme())
    val isDarkMode by root.isDarkMode.subscribeAsState()
    val childStack by root.childStack.subscribeAsState()
    AppTheme(isDarkTheme = isDarkMode) {
        Scaffold(
            topBar = {
                AppBar(
                    title = "Rocket Launches",
                    navigationIcon = {
                        if (childStack.active.instance is Child.DetailScreen) {
                            BackNavigation { root.navigation.pop() }
                        }
                    },
                    isDarkMode = isDarkMode,
                    onSwitchCheckedChange = { root.onUserSwitchedDarkMode(it) }
                )
            },
        ) {
            Children(
                modifier = Modifier.padding(it),
                stack = childStack,
                animation = stackAnimation(slide())
            ) { child ->
                when (val instance = child.instance) {
                    is Child.DetailScreen -> DetailScreen(instance.component)
                    is Child.HomeScreen -> HomeScreen(instance.component)
                }
            }
        }
    }
}

