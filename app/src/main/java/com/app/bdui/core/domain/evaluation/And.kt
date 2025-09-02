package com.app.bdui.core.domain.evaluation

import com.app.bdui.core.domain.value.BooleanValue
import com.app.bdui.core.domain.value.DynamicValue
import com.app.bdui.core.domain.entity.EvalContext
import com.app.bdui.core.domain.value.asBoolean
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine

internal data class And(val items: List<Evaluation>) : Evaluation {

    override fun eval(ctx: EvalContext): Flow<DynamicValue> {
        return combine(items.map { it.eval(ctx) }) { values ->
            BooleanValue(values.all(DynamicValue::asBoolean))
        }
    }
}