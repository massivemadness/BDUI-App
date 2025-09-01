package com.app.bdui.core.domain.evaluation

import com.app.bdui.core.domain.entity.BooleanValue
import com.app.bdui.core.domain.entity.IntegerValue
import com.app.bdui.core.domain.entity.DynamicValue
import com.app.bdui.core.domain.entity.StringValue
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

internal data class Literal(val value: DynamicValue) : Evaluation {

    constructor(string: String) : this(StringValue(string))
    constructor(boolean: Boolean) : this(BooleanValue(boolean))
    constructor(integer: Int) : this(IntegerValue(integer))

    override fun eval(ctx: EvalContext): Flow<DynamicValue> = flowOf(value)
}