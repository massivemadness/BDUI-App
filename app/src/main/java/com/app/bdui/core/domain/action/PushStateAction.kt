package com.app.bdui.core.domain.action

import com.app.bdui.core.domain.value.DynamicValue

internal data class PushStateAction(val ref: String, val value: DynamicValue) : Action {
    override val type = ActionType.PUSH_STATE
}