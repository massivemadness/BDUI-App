package com.app.bdui.core.data.network.action

import kotlinx.serialization.Serializable

@Serializable
internal data class ActionDto(
    val type: String? = null,
)