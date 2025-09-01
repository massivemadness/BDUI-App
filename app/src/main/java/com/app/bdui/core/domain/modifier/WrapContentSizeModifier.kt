package com.app.bdui.core.domain.modifier

import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.ui.Modifier

internal data object WrapContentSizeModifier : ModifierFactory {

    override fun Modifier.apply(scope: ModifierScope): Modifier {
        return this.wrapContentSize()
    }
}