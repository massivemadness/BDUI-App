package com.app.bdui.core.domain.evaluation

import com.app.bdui.core.domain.entity.BooleanValue
import com.app.bdui.core.domain.entity.DynamicValue
import com.app.bdui.core.domain.entity.asBoolean
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine

internal data class Or(val items: List<Evaluation>) : Evaluation {

    override fun eval(ctx: EvalContext): Flow<DynamicValue> {
        return combine(items.map { it.eval(ctx) }) { values ->
            BooleanValue(values.any(DynamicValue::asBoolean))
        }
    }
}