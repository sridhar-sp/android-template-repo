package com.droidstarter

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import com.droidstarter.designsystem.R
import com.droidstarter.designsystem.theme.DroidActivityTheme
import com.droidstarter.designsystem.theme.appDimens
import com.droidstarter.designsystem.theme.appShapes

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            var isDarkTheme by remember {
                mutableStateOf(true)
            }
            MaterialTheme.colorScheme.primary
            DroidActivityTheme(activity = this, isDarkTheme = isDarkTheme) {
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    Column {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically,
                        ) {
                            Text(
                                text = "Android Template",
                                style = MaterialTheme.typography.headlineMedium,
                                modifier = Modifier.weight(.9f),
                                textAlign = TextAlign.Center
                            )
                            ThemeIcon(isDarkTheme = isDarkTheme, onClick = { isDarkTheme = it })
                        }

                        ColorDemoWidget()
                        TextComponentDemoWidget()
                        ShapeComponentDemoWidget()
                    }
                }
            }
        }
    }
}

@Composable
private fun ColorDemoWidget() {
    WidgetTitleText(text = "Colors")
    LazyRow(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = MaterialTheme.appDimens.smallPadding),
        horizontalArrangement = Arrangement.spacedBy(MaterialTheme.appDimens.smallPadding)
    ) {
        item {
            ColorCard(
                name = "Primary",
                containerColor = MaterialTheme.colorScheme.primary,
                contentColor = MaterialTheme.colorScheme.onPrimary
            )
        }
        item {
            ColorCard(
                name = "Secondary",
                containerColor = MaterialTheme.colorScheme.secondary,
                contentColor = MaterialTheme.colorScheme.onSecondary
            )
        }
        item {
            ColorCard(
                name = "Tertiary",
                containerColor = MaterialTheme.colorScheme.tertiary,
                contentColor = MaterialTheme.colorScheme.onTertiary
            )
        }
        item {
            ColorCard(
                name = "Background",
                containerColor = MaterialTheme.colorScheme.background,
                contentColor = MaterialTheme.colorScheme.onBackground
            )
        }
        item {
            ColorCard(
                name = "Surface",
                containerColor = MaterialTheme.colorScheme.surface,
                contentColor = MaterialTheme.colorScheme.onSurface
            )
        }
        item {
            ColorCard(
                name = "Error",
                containerColor = MaterialTheme.colorScheme.error,
                contentColor = MaterialTheme.colorScheme.onError
            )
        }
    }
}

@Composable
fun ThemeIcon(
    isDarkTheme: Boolean, onClick: (isDarkTheme: Boolean) -> Unit
) {
    Card(onClick = { onClick(!isDarkTheme) }) {
        Image(
            painter = if (isDarkTheme) painterResource(id = R.drawable.ic_light_mode) else painterResource(
                id = R.drawable.ic_dark_mode
            ),
            contentDescription = "ThemeToggle",
            modifier = Modifier
                .size(MaterialTheme.appDimens.themeIconSize)
                .padding(MaterialTheme.appDimens.tinyPadding)
        )
    }

}

@Composable
fun ColorCard(name: String, containerColor: Color, contentColor: Color, shape: Shape = MaterialTheme.shapes.large) {
    Card(
        colors = CardDefaults.cardColors().copy(
            containerColor = containerColor, contentColor = contentColor
        ), modifier = Modifier
            .size(MaterialTheme.appDimens.cardWidgetSize)
            .border(
                width = MaterialTheme.appDimens.microPadding, color = MaterialTheme.colorScheme.onSurface, shape = shape
            ), shape = shape
    ) {
        Box(
            modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center
        ) {
            Text(
                text = name, style = MaterialTheme.typography.titleMedium, textAlign = TextAlign.Center
            )
        }
    }

}

@Composable
fun TextComponentDemoWidget() {
    WidgetTitleText(text = "Typography")
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(MaterialTheme.appDimens.typographyWidgetDemoHeight)
            .padding(MaterialTheme.appDimens.smallPadding)
            .border(
                width = MaterialTheme.appDimens.microPadding,
                color = MaterialTheme.colorScheme.onSurface,
                shape = MaterialTheme.shapes.large
            )
            .padding(MaterialTheme.appDimens.smallPadding)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(MaterialTheme.appDimens.smallPadding)
    ) {
        Text(text = "Display Large", style = MaterialTheme.typography.displayLarge)
        Text(text = "Display Medium", style = MaterialTheme.typography.displayMedium)
        Text(text = "Display Small", style = MaterialTheme.typography.displaySmall)
        Text(text = "Headline Large", style = MaterialTheme.typography.headlineLarge)
        Text(text = "Headline Medium", style = MaterialTheme.typography.headlineMedium)
        Text(text = "Headline Small", style = MaterialTheme.typography.headlineSmall)
        Text(text = "Title Large", style = MaterialTheme.typography.titleLarge)
        Text(text = "Title Medium", style = MaterialTheme.typography.titleMedium)
        Text(text = "Title Small", style = MaterialTheme.typography.titleSmall)
        Text(text = "Body Large", style = MaterialTheme.typography.bodyLarge)
        Text(text = "Body Medium", style = MaterialTheme.typography.bodyMedium)
        Text(text = "Body Small", style = MaterialTheme.typography.bodySmall)
        Text(text = "Label Large", style = MaterialTheme.typography.labelLarge)
        Text(text = "Label Medium", style = MaterialTheme.typography.labelMedium)
        Text(text = "Label Small", style = MaterialTheme.typography.labelSmall)
    }
}

@Composable
fun ShapeComponentDemoWidget() {
    WidgetTitleText(text = "Shapes")
    LazyRow(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = MaterialTheme.appDimens.smallPadding),
        horizontalArrangement = Arrangement.spacedBy(MaterialTheme.appDimens.smallPadding)
    ) {
        item {
            ColorCard(
                name = "Extra Small Shape",
                containerColor = MaterialTheme.colorScheme.outline,
                contentColor = MaterialTheme.colorScheme.outlineVariant,
                shape = MaterialTheme.shapes.extraSmall
            )
        }
        item {
            ColorCard(
                name = "Small Shape",
                containerColor = MaterialTheme.colorScheme.primary,
                contentColor = MaterialTheme.colorScheme.onPrimary,
                shape = MaterialTheme.shapes.small
            )
        }
        item {
            ColorCard(
                name = "Medium Shape",
                containerColor = MaterialTheme.colorScheme.secondary,
                contentColor = MaterialTheme.colorScheme.onSecondary,
                shape = MaterialTheme.shapes.medium
            )
        }
        item {
            ColorCard(
                name = "Large Shape",
                containerColor = MaterialTheme.colorScheme.tertiary,
                contentColor = MaterialTheme.colorScheme.onTertiary,
                shape = MaterialTheme.shapes.large
            )
        }
        item {
            ColorCard(
                name = "Extra Large Shape",
                containerColor = MaterialTheme.colorScheme.error,
                contentColor = MaterialTheme.colorScheme.onError,
                shape = MaterialTheme.shapes.extraLarge
            )
        }
        item {
            ColorCard(
                name = "Custom Circle Shape",
                containerColor = MaterialTheme.colorScheme.primaryContainer,
                contentColor = MaterialTheme.colorScheme.onPrimaryContainer,
                shape = MaterialTheme.appShapes.circleShape
            )
        }
    }
}

@Composable
private fun WidgetTitleText(text: String) {
    Text(
        text = text, style = MaterialTheme.typography.titleLarge, modifier = Modifier.padding(
            horizontal = MaterialTheme.appDimens.smallPadding, vertical = MaterialTheme.appDimens.mediumPadding
        )
    )
}