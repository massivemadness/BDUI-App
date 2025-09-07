package com.app.bdui.core.data.network.widget

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonElement

@Serializable
internal data class ScreenDto(
    @SerialName("state")
    val state: Map<String, JsonElement>? = null,
    @SerialName("templates")
    val templates: Map<String, WidgetDto>? = null,
    @SerialName("content")
    val content: WidgetDto,
)