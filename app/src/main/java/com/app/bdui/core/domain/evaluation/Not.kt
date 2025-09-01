package com.app.bdui.core.domain.evaluation

import com.app.bdui.core.domain.entity.BooleanValue
import com.app.bdui.core.domain.entity.StringValue
import com.app.bdui.core.domain.entity.DynamicValue
import com.app.bdui.core.domain.entity.asBoolean
import com.app.bdui.core.domain.entity.asString
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

internal data class Not(val target: Evaluation) : Evaluation {

    override fun eval(ctx: EvalContext): Flow<DynamicValue> {
        return target.eval(ctx).map { value ->
            BooleanValue(!value.asBoolean())
        }
    }
}