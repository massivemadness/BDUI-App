package com.app.bdui.core.domain.action

internal data class NavigateAction(val deeplink: String) : Action {
    override val type = ActionType.NAVIGATE
}