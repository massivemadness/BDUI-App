package com.app.bdui.core.domain.modifier

import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Immutable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Immutable
internal data class SizeModifier(val width: Int, val height: Int) : ModifierFactory {

    override fun Modifier.apply(scope: ModifierScope): Modifier {
        return this.size(
            width = width.dp,
            height = height.dp
        )
    }
}