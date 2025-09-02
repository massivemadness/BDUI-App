package com.app.bdui.core.domain.widget

import com.app.bdui.core.domain.modifier.ModifierFactory

internal data class SpacerWidget(
    override val id: String,
    val modifier: List<ModifierFactory>,
) : Widget {
    override val type = WidgetType.SPACER
}