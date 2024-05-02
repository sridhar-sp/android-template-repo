package com.droidstarter.coroutine

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun HomeScreen(viewModel: HomeViewModel = hiltViewModel()) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(4.dp), verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(
            text = "Single Core to Coroutine",
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth(),
            style = MaterialTheme.typography.headlineMedium
        )


        ColumnWithBorder {
            DefaultButton(text = "Show thread count", onClick = viewModel::readThreadCount)
            DefaultText(text = "Count ${viewModel.threadCount}")
        }

        ColumnWithBorder {
            DefaultButton(text = viewModel.runNThreadState, onClick = viewModel::runNThread)
            DefaultButton(text = viewModel.runNCoroutineState, onClick = viewModel::runNCoroutine)
            DefaultButton(text = "Counter ${viewModel.counter}", onClick = viewModel::incrementCounter)
        }

    }
}

@Composable
private fun ColumnWithBorder(content: @Composable () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .border(
                border = BorderStroke(width = 1.dp, color = MaterialTheme.colorScheme.primary),
                shape = RectangleShape
            )
            .padding(8.dp),
    ) {
        content()
    }
}

@Composable
private fun DefaultButton(text: String, onClick: () -> Unit) {
    Button(onClick = onClick) {
        Text(text = text)
    }
}

@Composable
private fun DefaultText(text: String) {
    Text(text = text)
}

@Composable
private fun DefaultSpacer() {
    Spacer(modifier = Modifier.height(8.dp))
}