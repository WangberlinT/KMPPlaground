package tianqi.kmp.practice.components

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.value.MutableValue
import com.arkivanov.decompose.value.Value
import kotlinx.coroutines.launch
import tianqi.kmp.practice.SpaceXSDK
import tianqi.kmp.practice.db.provideDatabaseDriverFactory
import tianqi.kmp.practice.model.RocketLaunch

class HomeScreenComponent(
    componentContext: ComponentContext,
    private val onNavigateToDetailScreen: (rocketLaunch: RocketLaunch) -> Unit
) : CoroutineComponent(componentContext) {
    private val _model: MutableValue<Model> = MutableValue(Model(emptyList()))
    val model: Value<Model> = _model
    private val spaceXApi by lazy { SpaceXSDK(provideDatabaseDriverFactory()) }

    override fun onCreate() {
        super.onCreate()
        componentScope.launch {
            runCatching {
                spaceXApi.getLaunches(false)
            }.onSuccess {
                _model.value = Model(it)
            }.onFailure {
                it.printStackTrace()
            }
        }
    }

    data class Model(
        val launches: List<RocketLaunch>
    )

    fun onEvent(event: HomeScreenEvents) {
        when (event) {
            is HomeScreenEvents.ItemClicked -> onNavigateToDetailScreen(event.rocketLaunch)
        }
    }
}

sealed interface HomeScreenEvents {
    data class ItemClicked(val rocketLaunch: RocketLaunch) : HomeScreenEvents
}