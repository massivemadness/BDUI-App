package com.app.bdui.core.domain.widget

import com.app.bdui.core.domain.entity.BduiAxis
import com.app.bdui.core.domain.modifier.ModifierFactory

internal data class ScrollWidget(
    override val id: String,
    val axis: BduiAxis,
    val children: List<Widget>,
    val modifier: List<ModifierFactory>,
) : Widget {
    override val type = WidgetType.SCROLL
}