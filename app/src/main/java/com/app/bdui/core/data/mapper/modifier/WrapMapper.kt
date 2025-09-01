package com.app.bdui.core.data.mapper.modifier

import com.app.bdui.core.domain.modifier.ModifierFactory
import com.app.bdui.core.data.network.modifier.WrapModifierDto
import com.app.bdui.core.domain.modifier.WrapContentHeightModifier
import com.app.bdui.core.domain.modifier.WrapContentSizeModifier
import com.app.bdui.core.domain.modifier.WrapContentWidthModifier

internal fun WrapModifierDto.toDomain(): ModifierFactory {
    return when (this) {
        WrapModifierDto.WrapContentWidth -> WrapContentWidthModifier
        WrapModifierDto.WrapContentHeight -> WrapContentHeightModifier
        WrapModifierDto.WrapContentSize -> WrapContentSizeModifier
    }
}