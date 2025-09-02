package com.app.bdui.core.domain.modifier

import androidx.compose.runtime.Immutable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Immutable
internal data class AlignModifier(
    val alignment: Alignment?,
    val horizontalAlignment: Alignment.Horizontal?,
    val verticalAlignment: Alignment.Vertical?,
) : ModifierFactory {

    override fun Modifier.apply(scope: ModifierScope): Modifier {
        return when {
            scope.boxScope != null -> with(scope.boxScope) {
                align(alignment ?: Alignment.TopStart)
            }
            scope.rowScope != null -> with(scope.rowScope) {
                align(verticalAlignment ?: Alignment.Top)
            }
            scope.columnScope != null -> with(scope.columnScope) {
                align(horizontalAlignment ?: Alignment.Start)
            }
            else -> this
        }
    }
}
