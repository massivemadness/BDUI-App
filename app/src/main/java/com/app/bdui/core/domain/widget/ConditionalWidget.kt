package com.app.bdui.core.domain.widget

import com.app.bdui.core.domain.evaluation.Evaluation
import com.app.bdui.core.domain.modifier.ModifierFactory

internal data class ConditionalWidget(
    override val id: String,
    val condition: Evaluation,
    val children: List<Widget>,
    val modifier: List<ModifierFactory>,
) : Widget {
    override val type = WidgetType.CONDITION
}