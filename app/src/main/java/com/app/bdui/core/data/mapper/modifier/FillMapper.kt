package com.app.bdui.core.data.mapper.modifier

import com.app.bdui.core.domain.modifier.ModifierFactory
import com.app.bdui.core.data.network.modifier.FillModifierDto
import com.app.bdui.core.domain.modifier.FillMaxHeightModifier
import com.app.bdui.core.domain.modifier.FillMaxSizeModifier
import com.app.bdui.core.domain.modifier.FillMaxWidthModifier

internal fun FillModifierDto.toDomain(): ModifierFactory {
    return when (this) {
        FillModifierDto.FillMaxWidth -> FillMaxWidthModifier
        FillModifierDto.FillMaxHeight -> FillMaxHeightModifier
        FillModifierDto.FillMaxSize -> FillMaxSizeModifier
    }
}