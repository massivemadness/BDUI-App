package com.app.bdui.core.ui.widget

import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.ui.Modifier
import androidx.compose.ui.util.fastForEach
import com.app.bdui.core.domain.modifier.ModifierFactory
import com.app.bdui.core.domain.modifier.ModifierScope
import com.app.bdui.core.ui.RenderWidget

@Immutable
internal data class RowWidgetNode(
    override val id: String,
    override val modifier: List<ModifierFactory>,
    val children: List<WidgetNode>,
) : WidgetNode {

    @Composable
    override fun Content(modifier: Modifier) {
        Row(modifier) {
            children.fastForEach { child ->
                RenderWidget(child, ModifierScope(rowScope = this))
            }
        }
    }
}