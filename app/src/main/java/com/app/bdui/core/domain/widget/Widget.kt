package com.app.bdui.core.domain.widget

internal sealed interface Widget {
    val id: String
    val type: WidgetType
}