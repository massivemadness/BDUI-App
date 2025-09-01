package com.app.bdui.core.ui.modifier

import androidx.compose.ui.Modifier
import androidx.compose.ui.util.fastForEach
import com.app.bdui.core.domain.modifier.ModifierFactory
import com.app.bdui.core.domain.modifier.ModifierScope

internal fun Modifier.scopedModifier(
    modifier: List<ModifierFactory>,
    scope: ModifierScope,
): Modifier {
    var chain = this
    modifier.fastForEach { param ->
        chain = with(param) {
            chain.apply(scope)
        }
    }
    return chain
}