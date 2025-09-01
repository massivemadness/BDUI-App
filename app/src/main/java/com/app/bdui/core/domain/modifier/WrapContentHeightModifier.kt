package com.app.bdui.core.domain.modifier

import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.ui.Modifier

internal data object WrapContentHeightModifier : ModifierFactory {

    override fun Modifier.apply(scope: ModifierScope): Modifier {
        return this.wrapContentHeight()
    }
}