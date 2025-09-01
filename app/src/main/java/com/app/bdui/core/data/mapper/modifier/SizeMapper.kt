package com.app.bdui.core.data.mapper.modifier

import com.app.bdui.core.data.network.modifier.SizeModifierDto
import com.app.bdui.core.domain.modifier.SizeModifier

internal fun SizeModifierDto.toDomain(): SizeModifier {
    return when {
        value != null -> SizeModifier(
            width = value,
            height = value
        )
        else -> SizeModifier(
            width = width ?: 0,
            height = height ?: 0,
        )
    }
}