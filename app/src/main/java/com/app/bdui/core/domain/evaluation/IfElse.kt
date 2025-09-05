package com.app.bdui.core.domain.evaluation

import com.app.bdui.core.domain.entity.EvalContext
import com.app.bdui.core.domain.value.DynamicValue
import com.app.bdui.core.domain.value.asBoolean
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapLatest

@OptIn(ExperimentalCoroutinesApi::class)
internal data class IfElse(
    val condition: Evaluation,
    val thenBranch: Evaluation,
    val elseBranch: Evaluation,
) : Evaluation {

    override fun eval(ctx: EvalContext): Flow<DynamicValue> {
        return condition.eval(ctx).flatMapLatest { value ->
            if (value.asBoolean()) {
                thenBranch.eval(ctx)
            } else {
                elseBranch.eval(ctx)
            }
        }
    }
}