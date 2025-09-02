package com.app.bdui.core.domain.widget

import com.app.bdui.core.domain.evaluation.Evaluation
import com.app.bdui.core.domain.modifier.ModifierFactory

internal data class TextWidget(
    override val id: String,
    val text: Evaluation,
    val modifier: List<ModifierFactory>,
) : Widget {
    override val type = WidgetType.TEXT
}