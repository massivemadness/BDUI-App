package com.app.bdui.core.domain.modifier

import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.ui.Modifier

internal data object WrapContentWidthModifier : ModifierFactory {

    override fun Modifier.apply(scope: ModifierScope): Modifier {
        return this.wrapContentWidth()
    }
}