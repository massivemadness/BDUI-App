package com.app.bdui.core.domain.modifier

import android.util.Log
import androidx.compose.ui.Modifier

internal object UnknownModifier : ModifierFactory {

    override fun Modifier.apply(scope: ModifierScope): Modifier {
        Log.e("UnknownModifier", "Can't apply unsupported modifier")
        return this
    }
}