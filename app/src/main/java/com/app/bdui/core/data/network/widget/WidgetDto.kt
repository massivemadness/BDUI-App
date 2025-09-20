package com.app.bdui.core.data.network.widget

import com.app.bdui.core.data.network.params.ParamsDto
import com.app.bdui.core.data.network.modifier.ModifierDto
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonElement

@Serializable
internal data class WidgetDto(
    @SerialName("id")
    val id: String? = null,
    @SerialName("type")
    val type: String? = null,
    @SerialName("name")
    val name: String? = null,
    @SerialName("params")
    val params: ParamsDto? = null,
    @SerialName("state")
    val state: Map<String, JsonElement>? = null,
    @SerialName("modifier")
    val modifier: List<ModifierDto>? = null,
    @SerialName("children")
    val children: List<WidgetDto>? = null,
)