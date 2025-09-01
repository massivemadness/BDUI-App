package com.app.bdui.core.domain.modifier

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.ui.Modifier

internal data object FillMaxWidthModifier : ModifierFactory {

    override fun Modifier.apply(scope: ModifierScope): Modifier {
        return this.fillMaxWidth()
    }
}