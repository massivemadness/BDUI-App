package com.app.bdui.core.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.app.bdui.core.data.repository.WidgetsRepositoryImpl
import com.app.bdui.core.domain.modifier.ModifierScope
import kotlinx.coroutines.launch

@Composable
fun RenderScreen() {
    val viewModel = RenderViewModel(WidgetsRepositoryImpl()) // TODO inject
    val viewState by viewModel.viewState.collectAsStateWithLifecycle()

    val scope = rememberCoroutineScope()
    val snackbarState = remember { SnackbarHostState() }

    LaunchedEffect(Unit) {
        viewModel.viewEvent.collect { event ->
            when (event) {
                is RenderViewModel.ViewEvent.Navigate -> {
                    // TODO implement
                }

                is RenderViewModel.ViewEvent.PopBackStack -> {
                    // TODO implement
                }

                is RenderViewModel.ViewEvent.ShowSnackbar -> {
                    scope.launch {
                        snackbarState.showSnackbar(event.message)
                    }
                }
            }
        }
    }

    Scaffold(
        snackbarHost = {
            SnackbarHost(hostState = snackbarState)
        }
    ) { contentPadding ->
        when {
            viewState.isLoading -> {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(contentPadding),
                ) {
                    CircularProgressIndicator(Modifier.align(Alignment.Center))
                }
            }

            viewState.isError -> {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(contentPadding),
                ) {
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
}