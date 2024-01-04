package tianqi.kmp.practice.components

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.value.MutableValue
import com.arkivanov.decompose.value.Value
import tianqi.kmp.practice.model.RocketLaunch

class DetailComponent(
    private val componentContext: ComponentContext,
    rocketLaunch: RocketLaunch
) : ComponentContext by componentContext {
    private val _model: MutableValue<Model> = MutableValue(Model(rocketLaunch))
    val model: Value<Model> = _model

    data class Model(
        val rocketLaunch: RocketLaunch
    )
}
