package com.app.bdui.core.domain.modifier

import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Immutable
import androidx.compose.ui.Modifier
import com.app.bdui.core.domain.entity.FillMode

@Immutable
internal data class FillSizeModifier(
    val fillMode: FillMode,
    val fraction: Float,
) : ModifierFactory {

    override fun Modifier.apply(scope: ModifierScope): Modifier {
        return when (fillMode) {
            FillMode.FillMaxWidth -> fillMaxWidth(fraction)
            FillMode.FillMaxHeight -> fillMaxHeight(fraction)
            FillMode.FillMaxSize -> fillMaxSize(fraction)
        }
    }
}