package com.app.bdui.core.domain.modifier

import androidx.compose.ui.Modifier

internal sealed interface ModifierFactory {

    fun Modifier.apply(scope: ModifierScope): Modifier
}