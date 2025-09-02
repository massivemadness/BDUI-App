package com.app.bdui.core.domain.entity

import com.app.bdui.core.domain.value.DynamicValue
import com.app.bdui.core.domain.value.NullValue
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

internal class EvalContext(initial: Snapshot) {

    private val state = initial.mapValues { MutableStateFlow(it.value) }.toMutableMap()

    fun get(ref: String): StateFlow<DynamicValue> {
        return state.getOrPut(ref) {
            MutableStateFlow(NullValue)
        }
    }

    fun set(ref: String, value: DynamicValue) {
        state.getOrPut(ref) {
            MutableStateFlow(value)
        }.value = value
    }

    fun snapshot(): Snapshot {
        return state.mapValues { (_, value) -> value.value }
    }
}