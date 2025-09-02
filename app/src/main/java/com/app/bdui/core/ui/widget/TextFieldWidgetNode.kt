package com.app.bdui.core.ui.widget

import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.app.bdui.core.domain.value.StringValue
import com.app.bdui.core.domain.modifier.ModifierFactory
import kotlinx.coroutines.flow.StateFlow

@Immutable
internal data class TextFieldWidgetNode(
    override val id: String,
    override val modifier: List<ModifierFactory>,
    val text: StateFlow<String>,
    val enabled: StateFlow<Boolean>,
    val onTextChanged: (StringValue) -> Unit,
) : WidgetNode {

    @Composable
    override fun Content(modifier: Modifier) {
        val text by text.collectAsStateWithLifecycle()
        val enabled by enabled.collectAsStateWithLifecycle()

        TextField(
            value = text,
            onValueChange = { value ->
                onTextChanged(StringValue(value))
            },
            enabled = enabled,
            modifier = modifier,
        )
    }
}