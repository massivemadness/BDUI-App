package com.app.bdui.core.data.network.modifier

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal enum class WrapModifierDto {
    @SerialName("content_width")
    WrapContentWidth,
    @SerialName("content_height")
    WrapContentHeight,
    @SerialName("content_size")
    WrapContentSize,
}