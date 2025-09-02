package com.app.bdui.core.domain.widget

import com.app.bdui.core.domain.modifier.ModifierFactory

internal sealed interface Widget {
    val id: String
    val type: WidgetType
    val modifier: List<ModifierFactory>
}