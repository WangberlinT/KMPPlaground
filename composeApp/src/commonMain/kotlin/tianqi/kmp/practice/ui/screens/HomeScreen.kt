package tianqi.kmp.practice.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.FilterQuality
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.arkivanov.decompose.extensions.compose.jetbrains.subscribeAsState
import io.kamel.core.Resource
import io.kamel.image.KamelImage
import io.kamel.image.asyncPainterResource
import tianqi.kmp.practice.components.HomeScreenComponent
import tianqi.kmp.practice.components.HomeScreenEvents
import tianqi.kmp.practice.getImageAsset
import tianqi.kmp.practice.model.RocketLaunch
import tianqi.kmp.practice.ui.LaunchTag

@Composable
fun HomeScreen(component: HomeScreenComponent) {
    val model by component.model.subscribeAsState()
    HomeScreenUi(
        list = model.launches,
        onClick = { component.onEvent(HomeScreenEvents.ItemClicked(it)) })
}

@Composable
fun HomeScreenUi(list: List<RocketLaunch>, onClick: ((RocketLaunch) -> Unit)? = null) {
    Surface {
        Column(
            modifier = Modifier.fillMaxWidth()
                .fillMaxHeight()
        ) {
            LazyColumn {
                items(list) { rocketLaunch ->
                    Box(
                        modifier = Modifier.padding(top = if (rocketLaunch != list.first()) 16.dp else 0.dp)
                            .fillMaxWidth()
                    ) {
                        RocketCard(rocketLaunch = rocketLaunch, onClick = onClick)
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RocketCard(rocketLaunch: RocketLaunch, onClick: ((RocketLaunch) -> Unit)? = null) {
    val placeholderImage = getImageAsset("placeholder.webp")
    Card(
        modifier = Modifier.fillMaxWidth()
            .wrapContentHeight()
            .padding(horizontal = 16.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 8.dp
        ),
        onClick = { onClick?.invoke(rocketLaunch) }
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .padding(16.dp)
            ) {
                val painterResource: Resource<Painter> = asyncPainterResource(
                    rocketLaunch.links.patch?.small ?: "",
                    filterQuality = FilterQuality.High,
                )
                KamelImage(
                    resource = painterResource,
                    contentDescription = "Rocket image",
                    contentScale = ContentScale.Fit,
                    onLoading = { _ ->
                        Image(
                            modifier = Modifier.fillMaxSize(),
                            contentDescription = "placeholder",
                            bitmap = placeholderImage,
                            contentScale = ContentScale.Crop
                        )
                    },
                    onFailure = {
                        Image(
                            modifier = Modifier.fillMaxSize(),
                            contentDescription = "placeholder",
                            bitmap = placeholderImage,
                            contentScale = ContentScale.Crop
                        )
                    },
                    modifier = Modifier.size(120.dp, 90.dp)
                        .clip(RoundedCornerShape(8.dp))
                        .background(Color.White)
                )
                Spacer(
                    modifier = Modifier.width(8.dp)
                )
                Column(
                    modifier = Modifier
                        .wrapContentSize(align = Alignment.TopStart)
                ) {
                    Text(
                        text = rocketLaunch.missionName,
                        style = MaterialTheme.typography.displayMedium
                    )
                    Text(
                        text = "Launch Year: ${rocketLaunch.launchYear}",
                        style = MaterialTheme.typography.bodyLarge
                    )
                }
            }
            LaunchTag(
                modifier = Modifier.align(Alignment.BottomEnd),
                isSuccess = rocketLaunch.launchSuccess == true
            )
        }

    }
}