package com.app.bdui.core.data.network.modifier

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class ModifierParamsDto(
    @SerialName("size")
    val size: Int? = null,
    @SerialName("width")
    val width: Int? = null,
    @SerialName("height")
    val height: Int? = null,
    @SerialName("fill")
    val fill: String? = null,
    @SerialName("wrap")
    val wrap: String? = null,
    @SerialName("fraction")
    val fraction: Float? = null,
    @SerialName("start")
    val start: Int? = null,
    @SerialName("top")
    val top: Int? = null,
    @SerialName("end")
    val end: Int? = null,
    @SerialName("bottom")
    val bottom: Int? = null,
    @SerialName("horizontal")
    val horizontal: Int? = null,
    @SerialName("vertical")
    val vertical: Int? = null,
    @SerialName("all")
    val all: Int? = null,
    @SerialName("top_start")
    val topStart: Int? = null,
    @SerialName("top_end")
    val topEnd: Int? = null,
    @SerialName("bottom_start")
    val bottomStart: Int? = null,
    @SerialName("bottom_end")
    val bottomEnd: Int? = null,
    @SerialName("color")
    val color: String? = null,
    @SerialName("corner_radius")
    val cornerRadius: Int? = null,
    @SerialName("alignment")
    val alignment: String? = null,
    @SerialName("weight")
    val weight: Float? = null,
)