package com.app.bdui.core.domain.entity

internal enum class FillMode(val value: String) {
    FillMaxWidth("max_width"),
    FillMaxHeight("max_height"),
    FillMaxSize("max_size");

    companion object {

        fun of(value: String?): FillMode {
            return entries.find { it.value == value } ?: FillMaxSize
        }
    }
}