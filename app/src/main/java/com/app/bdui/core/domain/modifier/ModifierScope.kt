package com.app.bdui.core.domain.modifier

import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.RowScope
import androidx.compose.runtime.Immutable

@Immutable
internal data class ModifierScope(
    val boxScope: BoxScope? = null,
    val columnScope: ColumnScope? = null,
    val rowScope: RowScope? = null,
)