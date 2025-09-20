package com.app.bdui.core.domain.modifier

import androidx.compose.foundation.background
import androidx.compose.runtime.Immutable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Immutable
internal data class BackgroundModifier(val color: Color) : ModifierFactory {

    override fun Modifier.apply(scope: ModifierScope): Modifier {
        return this.background(color)
    }
}
