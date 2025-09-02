package com.app.bdui.core.domain.widget

import com.app.bdui.core.domain.entity.EvalContext

internal class TemplateWidget(
    override val id: String,
    val name: String,
    val state: EvalContext,
) : Widget {
    override val type = WidgetType.TEMPLATE
}