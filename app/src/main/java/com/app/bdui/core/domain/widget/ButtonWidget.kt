package com.app.bdui.core.domain.widget

import com.app.bdui.core.domain.action.Action
import com.app.bdui.core.domain.evaluation.Evaluation
import com.app.bdui.core.domain.modifier.ModifierFactory

internal data class ButtonWidget(
    override val id: String,
    val text: Evaluation,
    val enabled: Evaluation,
    val onClick: List<Action>,
    val modifier: List<ModifierFactory>,
) : Widget {
    override val type = WidgetType.BUTTON
}