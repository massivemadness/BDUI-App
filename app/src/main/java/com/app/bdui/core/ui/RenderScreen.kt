package com.app.bdui.core.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.app.bdui.core.data.repository.WidgetsRepositoryImpl
import com.app.bdui.core.domain.modifier.ModifierScope

@Composable
fun RenderScreen() {
    val viewModel = RenderViewModel(WidgetsRepositoryImpl()) // TODO inject
    val viewState by viewModel.viewState.collectAsStateWithLifecycle()

    when {
        viewState.isLoading -> {
            Box(Modifier.fillMaxSize()) {
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.Center)
                )
            }
        }
        viewState.isError -> {
            Box(Modifier.fillMaxSize()) {
                Text(
                    text = "An error occurred",
                    modifier = Modifier.align(Alignment.Center)
                )
            }
        }
        else -> {
            viewState.widget?.let { widgetNode ->
                RenderWidget(widgetNode, ModifierScope())
            }
        }
    }
}