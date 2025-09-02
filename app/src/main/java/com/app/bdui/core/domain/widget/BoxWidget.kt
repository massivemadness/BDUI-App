package com.app.bdui.core.domain.widget

import com.app.bdui.core.domain.modifier.ModifierFactory

internal data class BoxWidget(
    override val id: String,
    val children: List<Widget>,
    val modifier: List<ModifierFactory>,
) : Widget {
    override val type = WidgetType.BOX
}