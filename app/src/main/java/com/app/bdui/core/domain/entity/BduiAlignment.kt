package com.app.bdui.core.domain.entity

internal enum class BduiAlignment(val value: String) {
    Top("top"),
    Bottom("bottom"),
    Start("start"),
    End("end"),
    TopStart("top_start"),
    TopCenter("top_center"),
    TopEnd("top_end"),
    CenterStart("center_start"),
    Center("center"),
    CenterEnd("center_end"),
    BottomStart("bottom_start"),
    BottomCenter("bottom_center"),
    BottomEnd("bottom_end");

    companion object {

        fun of(value: String?): BduiAlignment {
            return entries.find { it.value == value } ?: TopStart
        }
    }
}