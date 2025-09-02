package com.app.bdui.core.data.mapper.value

import com.app.bdui.core.domain.entity.BooleanValue
import com.app.bdui.core.domain.entity.DynamicValue
import com.app.bdui.core.domain.entity.IntegerValue
import com.app.bdui.core.domain.entity.NullValue
import com.app.bdui.core.domain.entity.StringValue
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