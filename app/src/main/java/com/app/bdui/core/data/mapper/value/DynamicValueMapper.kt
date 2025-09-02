package com.app.bdui.core.data.mapper.value

import com.app.bdui.core.domain.value.BooleanValue
import com.app.bdui.core.domain.value.DynamicValue
import com.app.bdui.core.domain.value.IntegerValue
import com.app.bdui.core.domain.value.NullValue
import com.app.bdui.core.domain.value.StringValue
import kotlinx.serialization.json.JsonPrimitive
import kotlinx.serialization.json.boolean
import kotlinx.serialization.json.booleanOrNull
import kotlinx.serialization.json.int
import kotlinx.serialization.json.intOrNull

internal fun JsonPrimitive?.toDynamicValue(): DynamicValue {
    return when {
        this == null -> NullValue
        isString -> StringValue(content)
        booleanOrNull != null -> BooleanValue(boolean)
        intOrNull != null -> IntegerValue(int)
        else -> NullValue
    }
}