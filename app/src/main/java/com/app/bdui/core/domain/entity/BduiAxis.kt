package com.app.bdui.core.domain.entity

internal enum class BduiAxis(val value: String) {
    Horizontal("horizontal"),
    Vertical("vertical");

    companion object {

        fun of(value: String?): BduiAxis {
            return entries.find { it.value == value } ?: Vertical
        }
    }
}