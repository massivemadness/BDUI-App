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

@Serializable(PaddingModifierDto.Serializer::class)
internal data class PaddingModifierDto(
    val start: Int? = null,
    val top: Int? = null,
    val end: Int? = null,
    val bottom: Int? = null,
    val horizontal: Int? = null,
    val vertical: Int? = null,
    val all: Int? = null,
) {
    object Serializer : KSerializer<PaddingModifierDto> {

        override val descriptor = buildClassSerialDescriptor("PaddingModifierDto")

        override fun deserialize(decoder: Decoder): PaddingModifierDto {
            val input = decoder as? JsonDecoder
                ?: error("PaddingModifierDto can be deserialized only from JSON")
            return when (val element = input.decodeJsonElement()) {
                is JsonPrimitive -> {
                    PaddingModifierDto(all = element.int)
                }
                is JsonObject -> {
                    val dto = input.json
                        .decodeFromJsonElement(ObjectDto.serializer(), element)
                    PaddingModifierDto(
                        start = dto.start,
                        top = dto.top,
                        end = dto.end,
                        bottom = dto.bottom,
                        horizontal = dto.horizontal,
                        vertical = dto.vertical,
                        all = dto.all,
                    )
                }
                else -> error("Unexpected JSON for PaddingModifierDto: $element")
            }
        }

        override fun serialize(encoder: Encoder, value: PaddingModifierDto) {
            error("Not needed")
        }
    }

    @Serializable
    private data class ObjectDto(
        @SerialName("start")
        val start: Int? = null,
        @SerialName("top")
        val top: Int? = null,
        @SerialName("end")
        val end: Int? = null,
        @SerialName("bottom")
        val bottom: Int? = null,
        @SerialName("horizontal")
        val horizontal: Int? = null,
        @SerialName("vertical")
        val vertical: Int? = null,
        @SerialName("all")
        val all: Int? = null
    )
}