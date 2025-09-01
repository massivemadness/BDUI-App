package com.app.bdui.core.domain.widget

import com.app.bdui.core.domain.action.Action
import com.app.bdui.core.domain.evaluation.Evaluation
import com.app.bdui.core.domain.modifier.ModifierFactory

internal data class ButtonWidget(
    override val id: String,
    override val modifier: List<ModifierFactory>,
    val text: Evaluation,
    val enabled: Evaluation,
    val onClick: List<Action>,
) : Widget {
    override val type = WidgetType.BUTTON
}