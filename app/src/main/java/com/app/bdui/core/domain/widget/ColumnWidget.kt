package com.app.bdui.core.domain.widget

import com.app.bdui.core.domain.widget.WidgetType
import com.app.bdui.core.domain.modifier.ModifierFactory

internal data class ColumnWidget(
    override val id: String,
    override val modifier: List<ModifierFactory>,
    val children: List<Widget>,
) : Widget {
    override val type = WidgetType.COLUMN
}