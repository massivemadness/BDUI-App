package com.app.bdui.core.domain.entity

import com.app.bdui.core.domain.widget.Widget

internal data class Screen(
    val state: Snapshot,
    val templates: Map<String, Widget>,
    val content: Widget,
)