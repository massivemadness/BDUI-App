package com.app.bdui.core.domain.modifier

import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.ui.Modifier

internal data object FillMaxHeightModifier : ModifierFactory {

    override fun Modifier.apply(scope: ModifierScope): Modifier {
        return this.fillMaxHeight()
    }
}