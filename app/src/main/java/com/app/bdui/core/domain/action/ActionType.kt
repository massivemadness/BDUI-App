package com.app.bdui.core.domain.action

internal enum class ActionType(val value: String) {
    PUSH_STATE("push_state"),
    SYNC_STATE("sync_state"),
    NAVIGATE("navigate"),
    GO_BACK("go_back"),
    SNACKBAR("snackbar"),
    UNSUPPORTED("unsupported");

    companion object {

        fun of(value: String?): ActionType? {
            return entries.find { it.value == value } ?: UNSUPPORTED
        }
    }
}