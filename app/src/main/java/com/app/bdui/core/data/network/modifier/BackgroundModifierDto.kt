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

@Serializable(BackgroundModifierDto.Serializer::class)
internal data class BackgroundModifierDto(val color: String) {

    object Serializer : KSerializer<BackgroundModifierDto> {

        override val descriptor = buildClassSerialDescriptor("BackgroundModifierDto")

        override fun deserialize(decoder: Decoder): BackgroundModifierDto {
            val input = decoder as? JsonDecoder
                ?: error("BackgroundModifierDto can be deserialized only from JSON")
            return when (val element = input.decodeJsonElement()) {
                is JsonPrimitive -> {
                    BackgroundModifierDto(color = element.content)
                }
                is JsonObject -> {
                    val dto = input.json
                        .decodeFromJsonElement(ObjectDto.serializer(), element)
                    BackgroundModifierDto(color = dto.color)
                }
                else -> error("Unexpected JSON for BackgroundModifierDto: $element")
            }
        }

        override fun serialize(encoder: Encoder, value: BackgroundModifierDto) {
            error("Not needed")
        }
    }

    @Serializable
    private data class ObjectDto(
        @SerialName("color")
        val color: String,
    )
}