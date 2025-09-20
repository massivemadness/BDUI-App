package com.app.bdui.core.domain.entity

internal enum class WrapMode(val value: String) {
    WrapContentWidth("content_width"),
    WrapContentHeight("content_height"),
    WrapContentSize("content_size");

    companion object {

        fun of(value: String?): WrapMode {
            return entries.find { it.value == value } ?: WrapContentSize
        }
    }
}