package com.app.bdui.core.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.util.fastForEach
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.app.bdui.core.data.repository.WidgetsRepositoryImpl
import com.app.bdui.core.domain.modifier.ModifierScope
import com.app.bdui.core.ui.RenderViewModel

@Composable
fun RenderScreen() {
    val viewModel = RenderViewModel(WidgetsRepositoryImpl()) // TODO inject
    val widgets by viewModel.widgets.collectAsStateWithLifecycle()

    widgets.fastForEach { widget ->
        RenderWidget(widget, ModifierScope())
    }
}