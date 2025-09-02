package com.app.bdui.core.domain.widget

import com.app.bdui.core.domain.evaluation.EvalContext
import com.app.bdui.core.domain.modifier.ModifierFactory

internal class TemplateWidget(
    override val id: String,
    override val modifier: List<ModifierFactory>,
    val name: String,
    val ctx: EvalContext,
) : Widget {
    override val type = WidgetType.TEMPLATE
}