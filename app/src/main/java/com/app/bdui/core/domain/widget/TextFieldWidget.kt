package com.app.bdui.core.domain.widget

import com.app.bdui.core.domain.widget.WidgetType
import com.app.bdui.core.domain.evaluation.Evaluation
import com.app.bdui.core.domain.modifier.ModifierFactory

internal data class TextFieldWidget(
    override val id: String,
    override val modifier: List<ModifierFactory>,
    val text: Evaluation,
    val enabled: Evaluation,
) : Widget {
    override val type = WidgetType.TEXT_FIELD
}