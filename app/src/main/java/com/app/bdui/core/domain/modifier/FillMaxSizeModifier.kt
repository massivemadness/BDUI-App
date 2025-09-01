package com.app.bdui.core.domain.modifier

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier

internal data object FillMaxSizeModifier : ModifierFactory {

    override fun Modifier.apply(scope: ModifierScope): Modifier {
        return this.fillMaxSize()
    }
}