package com.app.bdui.core.domain.repository

import com.app.bdui.core.domain.action.Action
import com.app.bdui.core.domain.action.SyncStateAction
import com.app.bdui.core.domain.entity.Screen
import com.app.bdui.core.domain.evaluation.EvalContext

internal interface WidgetsRepository {
    suspend fun loadScreen(screenId: String): Screen
    suspend fun syncActions(action: SyncStateAction, ctx: EvalContext): List<Action>
}