package com.app.bdui.core.ui.extensions

import com.app.bdui.core.domain.evaluation.Evaluation
import com.app.bdui.core.domain.evaluation.Literal
import com.app.bdui.core.domain.evaluation.Reference
import com.app.bdui.core.domain.evaluation.EvalContext
import com.app.bdui.core.domain.entity.DynamicValue
import com.app.bdui.core.domain.entity.IntegerValue
import com.app.bdui.core.domain.entity.StringValue
import com.app.bdui.core.domain.entity.asBoolean
import com.app.bdui.core.domain.entity.asInteger
import com.app.bdui.core.domain.entity.asString
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

internal fun Evaluation.evalString(ctx: EvalContext, scope: CoroutineScope): StateFlow<String> {
    if (this is Literal) {
        return MutableStateFlow(value.asString())
    }
    return eval(ctx).map(DynamicValue::asString).stateIn(
        scope = scope,
        started = SharingStarted.Eagerly,
        initialValue = if (this is Reference) {
            ctx.get(ref).value.asString()
        } else {
            StringValue.DEFAULT
        },
    )
}

internal fun Evaluation.evalBoolean(ctx: EvalContext, scope: CoroutineScope): StateFlow<Boolean> {
    if (this is Literal) {
        return MutableStateFlow(value.asBoolean())
    }
    return eval(ctx).map(DynamicValue::asBoolean).stateIn(
        scope = scope,
        started = SharingStarted.Eagerly,
        initialValue = if (this is Reference) {
            ctx.get(ref).value.asBoolean()
        } else {
            false
        },
    )
}

internal fun Evaluation.evalInteger(ctx: EvalContext, scope: CoroutineScope): StateFlow<Int> {
    if (this is Literal) {
        return MutableStateFlow(value.asInteger())
    }
    return eval(ctx).map(DynamicValue::asInteger).stateIn(
        scope = scope,
        started = SharingStarted.Eagerly,
        initialValue = if (this is Reference) {
            ctx.get(ref).value.asInteger()
        } else {
            IntegerValue.DEFAULT
        },
    )
}