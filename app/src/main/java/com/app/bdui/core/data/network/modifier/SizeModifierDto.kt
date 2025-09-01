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
import kotlinx.serialization.json.int

@Serializable(SizeModifierDto.Serializer::class)
internal data class SizeModifierDto(
    val value: Int? = null,
    val width: Int? = null,
    val height: Int? = null,
) {
    object Serializer : KSerializer<SizeModifierDto> {

        override val descriptor = buildClassSerialDescriptor("SizeModifierDto")

        override fun deserialize(decoder: Decoder): SizeModifierDto {
            val input = decoder as? JsonDecoder
                ?: error("SizeModifierDto can be deserialized only from JSON")
            return when (val element = input.decodeJsonElement()) {
                is JsonPrimitive -> {
                    SizeModifierDto(value = element.int)
                }
                is JsonObject -> {
                    val dto = input.json
                        .decodeFromJsonElement(ObjectDto.serializer(), element)
                    SizeModifierDto(
                        value = dto.value,
                        width = dto.width,
                        height = dto.height
                    )
                }
                else -> error("Unexpected JSON for SizeModifierDto: $element")
            }
        }

        override fun serialize(encoder: Encoder, value: SizeModifierDto) {
            error("Not needed")
        }
    }

    @Serializable
    private data class ObjectDto(
        @SerialName("value")
        val value: Int? = null,
        @SerialName("width")
        val width: Int? = null,
        @SerialName("height")
        val height: Int? = null,
    )
}