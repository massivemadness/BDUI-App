package com.app.bdui.core.data.mapper.modifier

import com.app.bdui.core.data.network.modifier.WeightModifierDto
import com.app.bdui.core.domain.modifier.SizeModifier
import com.app.bdui.core.domain.modifier.WeightModifier

internal fun WeightModifierDto.toDomain(): WeightModifier {
    return WeightModifier(
        weight = weight ?: 0f,
        fill = fill ?: true,
    )
}