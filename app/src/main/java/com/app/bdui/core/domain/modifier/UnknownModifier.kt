package com.app.bdui.core.domain.modifier

import android.util.Log
import androidx.compose.runtime.Immutable
import androidx.compose.ui.Modifier

@Immutable
internal data class UnknownModifier(val type: String?) : ModifierFactory {

    override fun Modifier.apply(scope: ModifierScope): Modifier {
        Log.e("UnknownModifier", "Unknown modifier: $type")
        return this
    }
}