package com.app.bdui.core.domain.widget

internal enum class WidgetType(val value: String) {
    TEMPLATE("template"),
    ROW("row"),
    COLUMN("column"),
    BOX("box"),
    TEXT("text"),
    BUTTON("button"),
    TEXT_FIELD("textfield");

    companion object {

        fun of(value: String?): WidgetType {
            return checkNotNull(entries.find { it.value == value })
        }
    }
}