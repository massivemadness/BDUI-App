package com.app.bdui.core.data.mapper.action

import com.app.bdui.core.data.mapper.value.toDynamicValue
import com.app.bdui.core.data.network.action.ActionDto
import com.app.bdui.core.domain.action.Action
import com.app.bdui.core.domain.action.ActionType
import com.app.bdui.core.domain.action.GoBackAction
import com.app.bdui.core.domain.action.NavigateAction
import com.app.bdui.core.domain.action.PushStateAction
import com.app.bdui.core.domain.action.SnackbarAction
import com.app.bdui.core.domain.action.SyncStateAction
import com.app.bdui.core.domain.action.UnsupportedAction

internal fun ActionDto.toDomain(): Action {
    return when (ActionType.of(type)) {
        ActionType.PUSH_STATE -> PushStateAction(
            ref = ref ?: error("Reference is required for type $type"),
            value = value.toDynamicValue(),
        )
        ActionType.SYNC_STATE -> SyncStateAction
        ActionType.NAVIGATE -> NavigateAction(deeplink = deeplink.orEmpty())
        ActionType.GO_BACK -> GoBackAction
        ActionType.SNACKBAR -> SnackbarAction(message = message.orEmpty())
        else -> UnsupportedAction
    }
}