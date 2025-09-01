package com.app.bdui.core.data.network.modifier

import kotlinx.serialization.KSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.descriptors.buildClassSerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import kotlinx.serialization.json.JsonDecoder
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.JsonPrimitive
import kotlinx.serialization.json.float

@Serializable(WeightModifierDto.Serializer::class)
internal data class WeightModifierDto(
    val weight: Float? = null,
    val fill: Boolean? = null,
) {
    object Serializer : KSerializer<WeightModifierDto> {

        override val descriptor = buildClassSerialDescriptor("WeightModifierDto")

        override fun deserialize(decoder: Decoder): WeightModifierDto {
            val input = decoder as? JsonDecoder
                ?: error("WeightModifierDto can be deserialized only from JSON")
            return when (val element = input.decodeJsonElement()) {
                is JsonPrimitive -> {
                    WeightModifierDto(weight = element.float)
                }
                is JsonObject -> {
                    val dto = input.json
                        .decodeFromJsonElement(ObjectDto.serializer(), element)
                    WeightModifierDto(
                        weight = dto.weight,
                        fill = dto.fill,
                    )
                }
                else -> error("Unexpected JSON for WeightModifierDto: $element")
            }
        }

        override fun serialize(encoder: Encoder, value: WeightModifierDto) {
            error("Not needed")
        }
    }

    @Serializable
    private data class ObjectDto(
        @SerialName("weight")
        val weight: Float? = null,
        @SerialName("fill")
        val fill: Boolean? = null,
    )
}