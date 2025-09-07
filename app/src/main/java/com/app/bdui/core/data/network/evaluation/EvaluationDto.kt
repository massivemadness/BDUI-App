package com.app.bdui.core.data.network.evaluation

import kotlinx.serialization.KSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.descriptors.buildClassSerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.JsonDecoder
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.JsonPrimitive

@Serializable(EvaluationDto.Serializer::class)
internal data class EvaluationDto(
    val reference: String? = null,
    val literal: JsonElement? = null,
    val empty: EvaluationDto? = null,
    val notEmpty: EvaluationDto? = null,
    val length: EvaluationDto? = null,
    val equals: List<EvaluationDto>? = null,
    val or: List<EvaluationDto>? = null,
    val and: List<EvaluationDto>? = null,
    val not: EvaluationDto? = null,
    val condition: EvaluationDto? = null,
    val thenBranch: EvaluationDto? = null,
    val elseBranch: EvaluationDto? = null,
) {

    object Serializer : KSerializer<EvaluationDto> {

        override val descriptor = buildClassSerialDescriptor("EvaluationDto")

        override fun deserialize(decoder: Decoder): EvaluationDto {
            val input = decoder as? JsonDecoder
                ?: error("EvaluationDto can be deserialized only from JSON")
            return when (val element = input.decodeJsonElement()) {
                is JsonPrimitive,
                is JsonArray -> {
                    EvaluationDto(literal = element)
                }
                is JsonObject -> {
                    val dto = input.json
                        .decodeFromJsonElement(ObjectDto.serializer(), element)
                    EvaluationDto(
                        reference = dto.reference,
                        literal = null,
                        empty = dto.empty,
                        notEmpty = dto.notEmpty,
                        length = dto.length,
                        equals = dto.equals,
                        or = dto.or,
                        and = dto.and,
                        not = dto.not,
                        condition = dto.condition,
                        thenBranch = dto.thenBranch,
                        elseBranch = dto.elseBranch,
                    )
                }
                else -> error("Unexpected JSON for EvaluationDto: $element")
            }
        }

        override fun serialize(encoder: Encoder, value: EvaluationDto) {
            error("Not needed")
        }
    }

    @Serializable
    private data class ObjectDto(
        @SerialName("ref")
        val reference: String? = null,
        @SerialName("empty")
        val empty: EvaluationDto? = null,
        @SerialName("notEmpty")
        val notEmpty: EvaluationDto? = null,
        @SerialName("length")
        val length: EvaluationDto? = null,
        @SerialName("equals")
        val equals: List<EvaluationDto>? = null,
        @SerialName("or")
        val or: List<EvaluationDto>? = null,
        @SerialName("and")
        val and: List<EvaluationDto>? = null,
        @SerialName("not")
        val not: EvaluationDto? = null,
        @SerialName("if")
        val condition: EvaluationDto? = null,
        @SerialName("then")
        val thenBranch: EvaluationDto? = null,
        @SerialName("else")
        val elseBranch: EvaluationDto? = null,
    )
}