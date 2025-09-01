package com.app.bdui.core.domain.evaluation

import com.app.bdui.core.domain.entity.DynamicValue
import kotlinx.coroutines.flow.Flow

internal sealed interface Evaluation {
    fun eval(ctx: EvalContext): Flow<DynamicValue>
}