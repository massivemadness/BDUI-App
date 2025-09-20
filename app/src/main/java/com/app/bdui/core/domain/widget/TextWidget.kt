package com.app.bdui.core.domain.widget

import com.app.bdui.core.domain.entity.BduiColor
import com.app.bdui.core.domain.entity.BduiTextStyle
import com.app.bdui.core.domain.evaluation.Evaluation
import com.app.bdui.core.domain.modifier.ModifierFactory

internal data class TextWidget(
    override val id: String,
    val text: Evaluation,
    val textStyle: BduiTextStyle,
    val textColor: BduiColor,
    val modifier: List<ModifierFactory>,
) : Widget {
    override val type = WidgetType.TEXT
}