package com.app.bdui.core.data.network.widget

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonPrimitive

@Serializable
internal data class ScreenDto(
    @SerialName("state")
    val state: Map<String, JsonPrimitive>,
    @SerialName("content")
    val content: List<WidgetDto>,
)