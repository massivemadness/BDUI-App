package com.app.bdui.core.domain.evaluation

import com.app.bdui.core.domain.value.BooleanValue
import com.app.bdui.core.domain.value.IntegerValue
import com.app.bdui.core.domain.value.DynamicValue
import com.app.bdui.core.domain.entity.EvalContext
import com.app.bdui.core.domain.value.StringValue
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

internal data class Literal(val value: DynamicValue) : Evaluation {

    constructor(string: String) : this(StringValue(string))
    constructor(boolean: Boolean) : this(BooleanValue(boolean))
    constructor(integer: Int) : this(IntegerValue(integer))

    override fun eval(ctx: EvalContext): Flow<DynamicValue> = flowOf(value)
}