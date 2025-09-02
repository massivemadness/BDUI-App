package com.app.bdui.core.data.network.action

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonPrimitive

@Serializable
internal data class ActionDto(
    @SerialName("type")
    val type: String? = null,
    @SerialName("id")
    val id: String? = null,
    @SerialName("ref")
    val ref: String? = null,
    @SerialName("value")
    val value: JsonPrimitive? = null,
    @SerialName("deeplink")
    val deeplink: String? = null,
    @SerialName("message")
    val message: String? = null,
)