package com.app.bdui.core.domain.action

internal data class SnackbarAction(val message: String) : Action {
    override val type = ActionType.SNACKBAR
}