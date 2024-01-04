package tianqi.kmp.practice.components

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.ExperimentalDecomposeApi
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.router.stack.pushNew
import com.arkivanov.decompose.value.MutableValue
import com.arkivanov.decompose.value.Value
import kotlinx.serialization.Serializable
import tianqi.kmp.practice.model.RocketLaunch

class RootComponent(
    val componentContext: ComponentContext
) : ComponentContext by componentContext {

    val navigation = StackNavigation<Configuration>()
    private val _isDarkMode = MutableValue(false)
    val isDarkMode: Value<Boolean> = _isDarkMode

    private var overrideSystemDarkMode: Boolean = false

    val childStack = childStack(
        source = navigation,
        serializer = Configuration.serializer(),
        initialConfiguration = Configuration.HomeScreen,
        handleBackButton = true,
        childFactory = ::createChild

    )

    fun onSystemDarkModeChanged(isDarkMode: Boolean) {
        if (overrideSystemDarkMode) return
        _isDarkMode.value = isDarkMode
    }

    fun onUserSwitchedDarkMode(isDarkMode: Boolean) {
        _isDarkMode.value = isDarkMode
        overrideSystemDarkMode = true
    }

    @OptIn(ExperimentalDecomposeApi::class)
    private fun createChild(
        config: Configuration,
        context: ComponentContext
    ) = when (config) {
        is Configuration.DetailScreen -> Child.DetailScreen(
            DetailComponent(
                componentContext = context,
                rocketLaunch = config.rocketLaunch
            )
        )

        Configuration.HomeScreen -> Child.HomeScreen(
            HomeScreenComponent(
                componentContext = context,
                onNavigateToDetailScreen = {
                    navigation.pushNew(Configuration.DetailScreen(rocketLaunch = it))
                }
            )
        )
    }

}

sealed class Child {
    data class HomeScreen(val component: HomeScreenComponent) : Child()
    data class DetailScreen(val component: DetailComponent) : Child()
}

@Serializable
sealed interface Configuration {
    @Serializable
    data object HomeScreen : Configuration

    @Serializable
    data class DetailScreen(val rocketLaunch: RocketLaunch) : Configuration
}