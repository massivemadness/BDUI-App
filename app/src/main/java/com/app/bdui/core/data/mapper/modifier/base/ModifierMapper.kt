package com.app.bdui.core.data.mapper.modifier.base

import com.app.bdui.core.data.mapper.modifier.toDomain
import com.app.bdui.core.domain.modifier.ModifierFactory
import com.app.bdui.core.data.network.modifier.base.ModifierDto
import com.app.bdui.core.domain.modifier.UnknownModifier

internal fun ModifierDto.toDomain(): ModifierFactory {
    return when {
        size != null -> size.toDomain()
        fill != null -> fill.toDomain()
        wrap != null -> wrap.toDomain()
        padding != null -> padding.toDomain()
        background != null -> background.toDomain()
        align != null -> align.toDomain()
        weight != null -> weight.toDomain()
        else -> UnknownModifier
    }
}