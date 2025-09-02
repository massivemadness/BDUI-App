package com.app.bdui.core.domain.action

internal data class SyncStateAction(val actionId: String) : Action {
    override val type = ActionType.SYNC_STATE
}