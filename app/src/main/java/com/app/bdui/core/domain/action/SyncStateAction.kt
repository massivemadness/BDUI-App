package com.app.bdui.core.domain.action

internal data class SyncStateAction(val widgetId: String) : Action {
    override val type = ActionType.SYNC_STATE
}