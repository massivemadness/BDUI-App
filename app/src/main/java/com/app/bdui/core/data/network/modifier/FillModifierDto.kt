package com.app.bdui.core.data.network.modifier

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal enum class FillModifierDto {
    @SerialName("max_width")
    FillMaxWidth,
    @SerialName("max_height")
    FillMaxHeight,
    @SerialName("max_size")
    FillMaxSize,
}