package com.app.bdui.core.domain.repository

import com.app.bdui.core.domain.action.Action
import com.app.bdui.core.domain.entity.Screen

internal interface WidgetsRepository {
    suspend fun loadScreen(screenId: String): Screen
    suspend fun dispatchAction(action: Action): List<Action>
}