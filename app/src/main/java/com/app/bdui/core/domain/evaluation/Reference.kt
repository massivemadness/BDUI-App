package com.app.bdui.core.domain.evaluation

import com.app.bdui.core.domain.entity.EvalContext

internal data class Reference(val ref: String) : Evaluation {
    override fun eval(ctx: EvalContext) = ctx.get(ref)
}