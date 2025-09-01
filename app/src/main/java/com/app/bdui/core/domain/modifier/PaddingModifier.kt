package com.app.bdui.core.domain.modifier

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Immutable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Immutable
internal data class PaddingModifier(
    val start: Int,
    val end: Int,
    val top: Int,
    val bottom: Int,
) : ModifierFactory {

    override fun Modifier.apply(scope: ModifierScope): Modifier {
        return this.padding(
            start = start.dp,
            end = end.dp,
            top = top.dp,
            bottom = bottom.dp
        )
    }
}
