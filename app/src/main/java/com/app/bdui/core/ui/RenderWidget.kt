package com.app.bdui.core.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.app.bdui.core.domain.modifier.ModifierScope
import com.app.bdui.core.ui.modifier.scopedModifier
import com.app.bdui.core.ui.widget.WidgetNode

@Composable
internal fun RenderWidget(widget: WidgetNode, scope: ModifierScope) {
    widget.Content(Modifier.scopedModifier(widget.modifier, scope))
}