package com.app.bdui.core.domain.widget

internal enum class WidgetType(val value: String) {
    TEMPLATE("template"),
    CONDITION("condition"),
    ROW("row"),
    COLUMN("column"),
    BOX("box"),
    TEXT("text"),
    BUTTON("button"),
    TEXT_FIELD("textfield"),
    SPACER("spacer");

    companion object {

        fun of(value: String?): WidgetType {
            return checkNotNull(entries.find { it.value == value })
        }
    }
}