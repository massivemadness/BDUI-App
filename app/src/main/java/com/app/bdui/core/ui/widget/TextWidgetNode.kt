package com.app.bdui.core.ui.widget

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.app.bdui.core.domain.modifier.ModifierFactory
import kotlinx.coroutines.flow.StateFlow

@Immutable
internal data class TextWidgetNode(
    override val id: String,
    override val modifier: List<ModifierFactory>,
    val text: StateFlow<String>,
) : WidgetNode {

    @Composable
    override fun Content(modifier: Modifier) {
        val text by text.collectAsStateWithLifecycle()

        Text(
            text = text,
            modifier = modifier,
        )
    }
}