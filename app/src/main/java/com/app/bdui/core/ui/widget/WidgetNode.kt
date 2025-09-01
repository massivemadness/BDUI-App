package com.app.bdui.core.ui.widget

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.app.bdui.core.domain.modifier.ModifierFactory

internal sealed interface WidgetNode {

    val id: String
    val modifier: List<ModifierFactory>

    @Composable
    fun Content(modifier: Modifier)
}