package com.app.bdui.core.domain.entity

internal enum class BduiColor(val value: String) {
    BLACK("black"),
    WHITE("white"),
    GRAY("gray");

    companion object {

        fun of(value: String?): BduiColor {
            return entries.find { it.value == value } ?: BLACK
        }
    }
}