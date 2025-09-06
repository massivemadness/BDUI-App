package com.app.bdui.core.ui.widget

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.util.fastForEach
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.app.bdui.core.domain.modifier.ModifierFactory
import com.app.bdui.core.domain.modifier.ModifierScope
import com.app.bdui.core.ui.RenderWidget
import kotlinx.coroutines.flow.StateFlow

@Immutable
internal data class ConditionalWidgetNode(
    override val id: String,
    override val modifier: List<ModifierFactory>,
    val visible: StateFlow<Boolean>,
    val children: List<WidgetNode>
) : WidgetNode {

    @Composable
    override fun Content(modifier: Modifier) {
        val visible by visible.collectAsStateWithLifecycle()
        AnimatedVisibility(
            visible = visible,
            modifier = modifier,
        ) {
            children.fastForEach { child ->
                RenderWidget(child, ModifierScope())
            }
        }
    }
}