package com.app.bdui.core.data.mapper.modifier

import com.app.bdui.core.domain.modifier.ModifierFactory
import com.app.bdui.core.data.network.modifier.PaddingModifierDto
import com.app.bdui.core.domain.modifier.PaddingModifier

internal fun PaddingModifierDto.toDomain(): ModifierFactory {
    return when {
        all != null -> PaddingModifier(
            start = all,
            top = all,
            end = all,
            bottom = all
        )
        horizontal != null || vertical != null -> PaddingModifier(
            start = horizontal ?: start ?: 0,
            top = vertical ?: top ?: 0,
            end = horizontal ?: end ?: 0,
            bottom = vertical ?: bottom ?: 0,
        )
        else -> PaddingModifier(
            start = start ?: 0,
            top = top ?: 0,
            end = end ?: 0,
            bottom = bottom ?: 0
        )
    }
}