package com.app.bdui.core.domain.evaluation

internal data class Reference(val ref: String) : Evaluation {
    override fun eval(ctx: EvalContext) = ctx.get(ref)
}