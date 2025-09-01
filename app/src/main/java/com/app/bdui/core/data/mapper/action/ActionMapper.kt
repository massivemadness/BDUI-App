package com.app.bdui.core.data.mapper.action

import com.app.bdui.core.data.network.action.ActionDto
import com.app.bdui.core.domain.action.Action
import com.app.bdui.core.domain.action.ActionType
import com.app.bdui.core.domain.action.GoBackAction
import com.app.bdui.core.domain.action.NavigateAction
import com.app.bdui.core.domain.action.PushStateAction
import com.app.bdui.core.domain.action.SnackbarAction
import com.app.bdui.core.domain.action.SyncStateAction
import com.app.bdui.core.domain.action.UnsupportedAction
import com.app.bdui.core.domain.entity.BooleanValue
import com.app.bdui.core.domain.entity.IntegerValue
import com.app.bdui.core.domain.entity.NullValue
import com.app.bdui.core.domain.entity.StringValue
import kotlinx.serialization.json.boolean
import kotlinx.serialization.json.booleanOrNull
import kotlinx.serialization.json.int
import kotlinx.serialization.json.intOrNull

internal fun ActionDto.toDomain(): Action {
    return when (ActionType.of(type)) {
        ActionType.PUSH_STATE -> PushStateAction(
            ref = ref ?: error("Reference is required for type $type"),
            value = when {
                value == null -> NullValue
                value.isString -> StringValue(value.content)
                value.booleanOrNull != null -> BooleanValue(value.boolean)
                value.intOrNull != null -> IntegerValue(value.int)
                else -> NullValue
            }
        )
        ActionType.SYNC_STATE -> SyncStateAction
        ActionType.NAVIGATE -> NavigateAction(deeplink = deeplink.orEmpty())
        ActionType.GO_BACK -> GoBackAction
        ActionType.SNACKBAR -> SnackbarAction(message = message.orEmpty())
        else -> UnsupportedAction
    }
}