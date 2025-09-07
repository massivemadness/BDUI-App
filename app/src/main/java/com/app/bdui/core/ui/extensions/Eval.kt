package com.app.bdui.core.ui.extensions

import com.app.bdui.core.domain.evaluation.Evaluation
import com.app.bdui.core.domain.evaluation.Literal
import com.app.bdui.core.domain.evaluation.Reference
import com.app.bdui.core.domain.entity.EvalContext
import com.app.bdui.core.domain.value.DynamicValue
import com.app.bdui.core.domain.value.IntegerValue
import com.app.bdui.core.domain.value.StringValue
import com.app.bdui.core.domain.value.asBoolean
import com.app.bdui.core.domain.value.asBooleanArray
import com.app.bdui.core.domain.value.asInteger
import com.app.bdui.core.domain.value.asIntegerArray
import com.app.bdui.core.domain.value.asString
import com.app.bdui.core.domain.value.asStringArray
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

internal fun Evaluation.evalStringArray(ctx: EvalContext, scope: CoroutineScope): StateFlow<List<String>> {
    if (this is Literal) {
        return MutableStateFlow(value.asStringArray())
    }
    return eval(ctx).map(DynamicValue::asStringArray).stateIn(
        scope = scope,
        started = SharingStarted.Eagerly,
        initialValue = if (this is Reference) {
            ctx.get(ref).value.asStringArray()
        } else {
            emptyList()
        },
    )
}

internal fun Evaluation.evalBooleanArray(ctx: EvalContext, scope: CoroutineScope): StateFlow<List<Boolean>> {
    if (this is Literal) {
        return MutableStateFlow(value.asBooleanArray())
    }
    return eval(ctx).map(DynamicValue::asBooleanArray).stateIn(
        scope = scope,
        started = SharingStarted.Eagerly,
        initialValue = if (this is Reference) {
            ctx.get(ref).value.asBooleanArray()
        } else {
            emptyList()
        },
    )
}

internal fun Evaluation.evalIntegerArray(ctx: EvalContext, scope: CoroutineScope): StateFlow<List<Int>> {
    if (this is Literal) {
        return MutableStateFlow(value.asIntegerArray())
    }
    return eval(ctx).map(DynamicValue::asIntegerArray).stateIn(
        scope = scope,
        started = SharingStarted.Eagerly,
        initialValue = if (this is Reference) {
            ctx.get(ref).value.asIntegerArray()
        } else {
            emptyList()
        },
    )
}