package com.app.bdui.core.domain.modifier

import androidx.compose.runtime.Immutable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Immutable
internal data class AlignModifier(val alignment: Alignment) : ModifierFactory {

    override fun Modifier.apply(scope: ModifierScope): Modifier {
        return when {
            scope.boxScope != null -> with(scope.boxScope) { align(alignment) }
            scope.rowScope != null -> this // TODO implement
            scope.columnScope != null -> this // TODO implement
            else -> this
        }
    }
}
