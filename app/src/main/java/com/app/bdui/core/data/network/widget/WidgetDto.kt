package com.app.bdui.core.data.network.widget

import com.app.bdui.core.data.network.params.ParamsDto
import com.app.bdui.core.data.network.modifier.base.ModifierDto
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class WidgetDto(
    @SerialName("id")
    val id: String? = null,
    @SerialName("type")
    val type: String? = null,
    @SerialName("params")
    val params: ParamsDto? = null,
    @SerialName("modifier")
    val modifier: List<ModifierDto>? = null,
    @SerialName("children")
    val children: List<WidgetDto>? = null,
)