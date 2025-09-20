package com.app.bdui.core.data.network.widget

import com.app.bdui.core.data.network.action.ActionDto
import com.app.bdui.core.data.network.evaluation.EvaluationDto
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class WidgetParamsDto(
    @SerialName("text")
    val text: EvaluationDto? = null,
    @SerialName("enabled")
    val enabled: EvaluationDto? = null,
    @SerialName("onClick")
    val onClick: List<ActionDto>? = null,
    @SerialName("visible")
    val visible: EvaluationDto? = null,
    @SerialName("axis")
    val axis: String? = null,
    @SerialName("color")
    val color: String? = null,
    @SerialName("text_style")
    val textStyle: String? = null,
)