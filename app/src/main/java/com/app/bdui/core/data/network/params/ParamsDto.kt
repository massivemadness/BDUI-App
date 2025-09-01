package com.app.bdui.core.data.network.params

import com.app.bdui.core.data.network.action.ActionDto
import com.app.bdui.core.data.network.evaluation.EvaluationDto
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class ParamsDto(
    @SerialName("text")
    val text: EvaluationDto? = null,
    @SerialName("enabled")
    val enabled: EvaluationDto? = null,
    @SerialName("onClick")
    val onClick: List<ActionDto>? = null,
)
