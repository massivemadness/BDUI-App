package com.app.bdui.core.domain.action

import com.app.bdui.core.domain.evaluation.Evaluation

internal data class PushStateAction(val ref: String, val value: Evaluation) : Action {
    override val type = ActionType.PUSH_STATE
}