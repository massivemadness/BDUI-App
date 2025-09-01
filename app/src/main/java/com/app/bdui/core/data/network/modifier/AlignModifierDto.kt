package com.app.bdui.core.data.network.modifier

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal enum class AlignModifierDto {
    @SerialName("top_start")
    TopStart,
    @SerialName("top_center")
    TopCenter,
    @SerialName("top_end")
    TopEnd,
    @SerialName("center_start")
    CenterStart,
    @SerialName("center")
    Center,
    @SerialName("center_end")
    CenterEnd,
    @SerialName("bottom_start")
    BottomStart,
    @SerialName("bottom_center")
    BottomCenter,
    @SerialName("bottom_end")
    BottomEnd,
}