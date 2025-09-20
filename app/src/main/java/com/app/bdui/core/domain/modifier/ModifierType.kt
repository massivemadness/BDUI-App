package com.app.bdui.core.domain.modifier

internal enum class ModifierType(val value: String) {
    SIZE("size"),
    FILL("fill"),
    WRAP("wrap"),
    PADDING("padding"),
    BACKGROUND("background"),
    CORNERS("corners"),
    ALIGN("align"),
    WEIGHT("weight"),
    UNKNOWN("");

    companion object {

        fun of(value: String?): ModifierType {
            return entries.find { it.value == value } ?: UNKNOWN
        }
    }
}