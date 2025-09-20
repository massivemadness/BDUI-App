package com.app.bdui.core.data.network.modifier

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class ModifierDto(
    @SerialName("type")
    val type: String? = null,
    @SerialName("value")
    val value: ModifierValueDto? = null,
)