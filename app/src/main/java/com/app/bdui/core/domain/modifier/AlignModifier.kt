package com.app.bdui.core.domain.modifier

import androidx.compose.runtime.Immutable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Immutable
internal data class AlignModifier(val alignment: Alignment) : ModifierFactory {

    override fun Modifier.apply(scope: ModifierScope): Modifier {
        return scope.boxScope?.run { align(alignment) } ?: this
    }
}
