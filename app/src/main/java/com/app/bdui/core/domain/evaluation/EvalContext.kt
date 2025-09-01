package com.app.bdui.core.domain.evaluation

import com.app.bdui.core.domain.entity.DynamicValue
import com.app.bdui.core.domain.entity.NullValue
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

internal class EvalContext(initial: Map<String, DynamicValue>) {

    private val state = initial.mapValues { MutableStateFlow(it.value) }.toMutableMap()

    fun get(ref: String): StateFlow<DynamicValue> {
        return state.getOrPut(ref) {
            MutableStateFlow(NullValue)
        }
    }

    fun set(ref: String, value: DynamicValue) {
        state[ref]?.value = value
    }
}