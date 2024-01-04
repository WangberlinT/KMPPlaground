package tianqi.kmp.practice

import androidx.compose.ui.window.ComposeUIViewController
import tianqi.kmp.practice.components.RootComponent

fun MainViewController(rootComponent: RootComponent) = ComposeUIViewController {
    App(rootComponent)
}
