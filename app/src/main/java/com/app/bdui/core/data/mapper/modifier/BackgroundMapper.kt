package com.app.bdui.core.data.mapper.modifier

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.core.graphics.toColorInt
import com.app.bdui.core.domain.modifier.ModifierFactory
import com.app.bdui.core.data.network.modifier.BackgroundModifierDto
import com.app.bdui.core.domain.modifier.BackgroundModifier

internal fun BackgroundModifierDto.toDomain(): ModifierFactory {
    return BackgroundModifier(
        color = Color(color.toColorInt()),
        shape = if (cornerRadius > 0) {
            RoundedCornerShape(cornerRadius)
        } else {
            RectangleShape
        }
    )
}