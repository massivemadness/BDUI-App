package com.app.bdui.core.domain.modifier

import androidx.compose.runtime.Immutable
import androidx.compose.ui.Modifier

@Immutable
internal data class WeightModifier(val weight: Float) : ModifierFactory {

    override fun Modifier.apply(scope: ModifierScope): Modifier {
        return when {
            scope.columnScope != null -> with(scope.columnScope) { weight(weight) }
            scope.rowScope != null -> with(scope.rowScope) { weight(weight) }
            else -> this
        }
    }
}