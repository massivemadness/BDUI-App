package com.app.bdui.core.data.mapper.modifier

import androidx.compose.ui.Alignment
import com.app.bdui.core.data.network.modifier.AlignModifierDto
import com.app.bdui.core.domain.modifier.AlignModifier

internal fun AlignModifierDto.toDomain(): AlignModifier {
    return AlignModifier(
        alignment = when (this) {
            AlignModifierDto.TopStart -> Alignment.TopStart
            AlignModifierDto.TopCenter -> Alignment.TopCenter
            AlignModifierDto.TopEnd -> Alignment.TopEnd
            AlignModifierDto.CenterStart -> Alignment.CenterStart
            AlignModifierDto.Center -> Alignment.Center
            AlignModifierDto.CenterEnd -> Alignment.CenterEnd
            AlignModifierDto.BottomStart -> Alignment.BottomStart
            AlignModifierDto.BottomCenter -> Alignment.BottomCenter
            AlignModifierDto.BottomEnd -> Alignment.BottomEnd
            else -> null
        },
        horizontalAlignment = when (this) {
            AlignModifierDto.Start -> Alignment.Start
            AlignModifierDto.Center -> Alignment.CenterHorizontally
            AlignModifierDto.End -> Alignment.End
            else -> null
        },
        verticalAlignment = when (this) {
            AlignModifierDto.Top -> Alignment.Top
            AlignModifierDto.Center -> Alignment.CenterVertically
            AlignModifierDto.Bottom -> Alignment.Bottom
            else -> null
        }
    )
}