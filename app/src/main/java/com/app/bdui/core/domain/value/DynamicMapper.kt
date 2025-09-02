package com.app.bdui.core.domain.value

internal fun DynamicValue.asString(): String {
    return when (this) {
        is StringValue -> this.value
        is BooleanValue -> this.value.toString()
        is IntegerValue -> this.value.toString()
        else -> StringValue.DEFAULT
    }
}

internal fun DynamicValue.asBoolean(): Boolean {
    return when (this) {
        is BooleanValue -> this.value
        is StringValue -> this.value.equals("true", ignoreCase = true)
        is IntegerValue -> this.value != 0
        else -> false
    }
}

internal fun DynamicValue.asInteger(): Int {
    return when (this) {
        is IntegerValue -> this.value
        is BooleanValue -> if (value) 1 else 0
        is StringValue -> this.value.toIntOrNull() ?: IntegerValue.DEFAULT
        else -> IntegerValue.DEFAULT
    }
}