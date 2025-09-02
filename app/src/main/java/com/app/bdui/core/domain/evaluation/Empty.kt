package com.app.bdui.core.domain.evaluation

import com.app.bdui.core.domain.value.BooleanValue
import com.app.bdui.core.domain.value.DynamicValue
import com.app.bdui.core.domain.entity.EvalContext
import com.app.bdui.core.domain.value.asString
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

internal data class Empty(val target: Evaluation) : Evaluation {

    override fun eval(ctx: EvalContext): Flow<DynamicValue> {
        return target.eval(ctx).map { value ->
            BooleanValue(value.asString().isEmpty())
        }
    }
}