package com.app.bdui.core.ui.widget

import androidx.compose.foundation.layout.Spacer
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.ui.Modifier
import com.app.bdui.core.domain.modifier.ModifierFactory

@Immutable
internal data class SpacerWidgetNode(
    override val id: String,
    override val modifier: List<ModifierFactory>,
) : WidgetNode {

    @Composable
    override fun Content(modifier: Modifier) {
        Spacer(modifier)
    }
}