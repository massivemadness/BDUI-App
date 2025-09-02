package com.app.bdui.core.domain.repository

import com.app.bdui.core.domain.action.Action
import com.app.bdui.core.domain.entity.Screen
import com.app.bdui.core.domain.entity.Snapshot

internal interface WidgetsRepository {
    suspend fun loadScreen(screenId: String): Screen
    suspend fun loadActions(screenId: String, actionId: String, snapshot: Snapshot): List<Action>
}