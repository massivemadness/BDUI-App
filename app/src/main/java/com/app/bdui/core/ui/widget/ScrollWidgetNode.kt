package com.app.bdui.core.ui.widget

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.ui.Modifier
import androidx.compose.ui.util.fastForEach
import com.app.bdui.core.domain.entity.BduiAxis
import com.app.bdui.core.domain.modifier.ModifierFactory
import com.app.bdui.core.domain.modifier.ModifierScope
import com.app.bdui.core.ui.RenderWidget

@Immutable
internal data class ScrollWidgetNode(
    override val id: String,
    override val modifier: List<ModifierFactory>,
    val axis: BduiAxis,
    val children: List<WidgetNode>
) : WidgetNode {

    @Composable
    override fun Content(modifier: Modifier) {
        when (axis) {
            BduiAxis.Horizontal -> {
                Box(modifier.horizontalScroll(rememberScrollState())) {
                    children.fastForEach { child ->
                        RenderWidget(child, ModifierScope(boxScope = this))
                    }
                }
            }
            BduiAxis.Vertical -> {
                Box(modifier.verticalScroll(rememberScrollState())) {
                    children.fastForEach { child ->
                        RenderWidget(child, ModifierScope(boxScope = this))
                    }
                }
            }
        }
    }
}