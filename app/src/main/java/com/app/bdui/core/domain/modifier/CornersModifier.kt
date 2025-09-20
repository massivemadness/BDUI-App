package com.app.bdui.core.domain.modifier

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Immutable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp

@Immutable
internal data class CornersModifier(
    val topStart: Int,
    val topEnd: Int,
    val bottomStart: Int,
    val bottomEnd: Int,
) : ModifierFactory {

    override fun Modifier.apply(scope: ModifierScope): Modifier {
        val shape = RoundedCornerShape(
            topStart = topStart.dp,
            topEnd = topEnd.dp,
            bottomStart = bottomStart.dp,
            bottomEnd = bottomEnd.dp
        )
        return clip(shape)
    }
}