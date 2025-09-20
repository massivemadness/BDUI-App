package com.app.bdui.core.domain.entity

internal enum class BduiTextStyle(val value: String) {
    BODY_1("body_1"),
    BODY_2("body_2"),
    BODY_3("body_3");

    companion object {

        fun of(value: String?): BduiTextStyle {
            return entries.find { it.value == value } ?: BODY_1
        }
    }
}