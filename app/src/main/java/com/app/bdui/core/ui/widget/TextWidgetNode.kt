package com.app.bdui.core.ui.widget

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.app.bdui.core.domain.entity.BduiColor
import com.app.bdui.core.domain.entity.BduiTextStyle
import com.app.bdui.core.domain.modifier.ModifierFactory
import kotlinx.coroutines.flow.StateFlow

@Immutable
internal data class TextWidgetNode(
    override val id: String,
    override val modifier: List<ModifierFactory>,
    val text: StateFlow<String>,
    val textStyle: BduiTextStyle,
    val textColor: BduiColor,
) : WidgetNode {

    @Composable
    override fun Content(modifier: Modifier) {
        val text by text.collectAsStateWithLifecycle()

        Text(
            text = text,
            style = when (textStyle) {
                BduiTextStyle.BODY_1 -> MaterialTheme.typography.bodySmall
                BduiTextStyle.BODY_2 -> MaterialTheme.typography.bodyMedium
                BduiTextStyle.BODY_3 -> MaterialTheme.typography.bodyLarge
            },
            color = when (textColor) {
                BduiColor.BLACK -> Color.Black
                BduiColor.WHITE -> Color.White
                BduiColor.GRAY -> Color.Gray
            },
            modifier = modifier,
        )
    }
}