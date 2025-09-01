package com.app.bdui.core.data.network.modifier.base

import com.app.bdui.core.data.network.modifier.AlignModifierDto
import com.app.bdui.core.data.network.modifier.BackgroundModifierDto
import com.app.bdui.core.data.network.modifier.FillModifierDto
import com.app.bdui.core.data.network.modifier.PaddingModifierDto
import com.app.bdui.core.data.network.modifier.SizeModifierDto
import com.app.bdui.core.data.network.modifier.WeightModifierDto
import com.app.bdui.core.data.network.modifier.WrapModifierDto
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class ModifierDto(
    @SerialName("size")
    val size: SizeModifierDto? = null,
    @SerialName("fill")
    val fill: FillModifierDto? = null,
    @SerialName("wrap")
    val wrap: WrapModifierDto? = null,
    @SerialName("padding")
    val padding: PaddingModifierDto? = null,
    @SerialName("background")
    val background: BackgroundModifierDto? = null,
    @SerialName("align")
    val align: AlignModifierDto? = null,
    @SerialName("weight")
    val weight: WeightModifierDto? = null,
)