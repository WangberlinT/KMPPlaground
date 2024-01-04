package tianqi.kmp.practice.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
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
import tianqi.kmp.practice.components.DetailComponent
import tianqi.kmp.practice.getImageAsset
import tianqi.kmp.practice.model.RocketLaunch
import tianqi.kmp.practice.ui.LaunchTag

@Composable
fun DetailScreen(component: DetailComponent) {
    val model by component.model.subscribeAsState()
    val rocketLaunch = model.rocketLaunch
    DetailScreenUi(rocketLaunch = rocketLaunch)
}

@Composable
fun DetailScreenUi(rocketLaunch: RocketLaunch) {
    val placeholderImage = getImageAsset("placeholder.webp")
    Surface(modifier = Modifier.fillMaxSize()) {
        val painterResource: Resource<Painter> = asyncPainterResource(
            rocketLaunch.links.patch?.small ?: "",
            filterQuality = FilterQuality.High,
        )
        Column {
            Box {
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
                    modifier = Modifier.fillMaxWidth()
                        .height(200.dp)
                        .clip(RoundedCornerShape(8.dp))
                        .background(Color.White)
                )
                LaunchTag(
                    isSuccess = rocketLaunch.launchSuccess == true,
                    modifier = Modifier.align(Alignment.BottomEnd)
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = rocketLaunch.details ?: "",
                style = MaterialTheme.typography.bodyLarge,
            )

        }
    }
}