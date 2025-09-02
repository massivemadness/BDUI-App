package com.app.bdui.core.domain.entity

import com.app.bdui.core.domain.widget.Widget

internal data class Screen(
    val state: Map<String, DynamicValue>,
    val templates: Map<String, Widget>,
    val content: Widget,
)