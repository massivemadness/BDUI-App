package com.app.bdui.core.domain.action

internal sealed interface Action {
    val type: ActionType
}