package com.app.bdui.core.data.network.action

import com.app.bdui.core.data.network.evaluation.EvaluationDto
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class ActionDto(
    @SerialName("type")
    val type: String? = null,
    @SerialName("id")
    val id: String? = null,
    @SerialName("ref")
    val ref: String? = null,
    @SerialName("value")
    val value: EvaluationDto? = null,
    @SerialName("deeplink")
    val deeplink: String? = null,
    @SerialName("message")
    val message: String? = null,
)