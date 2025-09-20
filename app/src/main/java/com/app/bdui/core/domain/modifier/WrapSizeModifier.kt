package com.app.bdui.core.domain.modifier

import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.runtime.Immutable
import androidx.compose.ui.Modifier
import com.app.bdui.core.domain.entity.WrapMode

@Immutable
internal data class WrapSizeModifier(val wrapMode: WrapMode) : ModifierFactory {

    override fun Modifier.apply(scope: ModifierScope): Modifier {
        return when (wrapMode) {
            WrapMode.WrapContentWidth -> wrapContentWidth()
            WrapMode.WrapContentHeight -> wrapContentHeight()
            WrapMode.WrapContentSize -> wrapContentSize()
        }
    }
}