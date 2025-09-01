package com.app.bdui.core.domain.widget

import com.app.bdui.core.domain.widget.WidgetType
import com.app.bdui.core.domain.evaluation.Evaluation
import com.app.bdui.core.domain.modifier.ModifierFactory

internal data class TextWidget(
    override val id: String,
    override val modifier: List<ModifierFactory>,
    val text: Evaluation,
) : Widget {
    override val type = WidgetType.TEXT
}