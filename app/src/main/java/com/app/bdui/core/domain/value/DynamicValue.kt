package com.app.bdui.core.domain.value

import androidx.compose.runtime.Immutable

internal sealed interface DynamicValue

@Immutable
internal data class BooleanValue(val value: Boolean) : DynamicValue

@Immutable
internal data class IntegerValue(val value: Int) : DynamicValue {
    companion object {
        const val DEFAULT = 0
    }
}

@Immutable
internal data class StringValue(val value: String) : DynamicValue {
    companion object {
        const val DEFAULT = ""
    }
}

internal data object NullValue : DynamicValue