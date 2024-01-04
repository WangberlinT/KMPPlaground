package tianqi.kmp.practice.components

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.value.MutableValue
import com.arkivanov.decompose.value.Value
import kotlinx.coroutines.launch
import tianqi.kmp.practice.model.RocketLaunch
import tianqi.kmp.practice.network.SpaceXApi

class HomeScreenComponent(
    componentContext: ComponentContext,
    private val onNavigateToDetailScreen: (rocketLaunch: RocketLaunch) -> Unit
) : CoroutineComponent(componentContext) {
    private val _model: MutableValue<Model> = MutableValue(Model(emptyList()))
    val model: Value<Model> = _model
    private val spaceXApi by lazy { SpaceXApi() }

    override fun onCreate() {
        super.onCreate()
        componentScope.launch {
            runCatching {
                spaceXApi.getAllLaunches()
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