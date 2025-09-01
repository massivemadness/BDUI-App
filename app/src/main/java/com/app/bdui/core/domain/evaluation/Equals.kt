package com.app.bdui.core.domain.evaluation

import com.app.bdui.core.domain.entity.BooleanValue
import com.app.bdui.core.domain.entity.DynamicValue
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine

internal data class Equals(val left: Evaluation, val right: Evaluation) : Evaluation {

    override fun eval(ctx: EvalContext): Flow<DynamicValue> {
        return combine(left.eval(ctx), right.eval(ctx)) { (left, right) ->
            BooleanValue(left == right)
        }
    }
}