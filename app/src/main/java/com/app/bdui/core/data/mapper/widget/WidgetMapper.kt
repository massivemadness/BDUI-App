package com.app.bdui.core.data.mapper.widget

import com.app.bdui.core.data.mapper.action.toDomain
import com.app.bdui.core.data.mapper.evaluation.toDomain
import com.app.bdui.core.data.mapper.modifier.base.toDomain
import com.app.bdui.core.data.network.action.ActionDto
import com.app.bdui.core.data.network.modifier.base.ModifierDto
import com.app.bdui.core.data.network.widget.ScreenDto
import com.app.bdui.core.data.network.widget.WidgetDto
import com.app.bdui.core.domain.action.Action
import com.app.bdui.core.domain.evaluation.Literal
import com.app.bdui.core.domain.entity.BooleanValue
import com.app.bdui.core.domain.entity.IntegerValue
import com.app.bdui.core.domain.entity.StringValue
import com.app.bdui.core.domain.widget.BoxWidget
import com.app.bdui.core.domain.widget.ButtonWidget
import com.app.bdui.core.domain.widget.ColumnWidget
import com.app.bdui.core.domain.entity.Screen
import com.app.bdui.core.domain.widget.RowWidget
import com.app.bdui.core.domain.widget.TextFieldWidget
import com.app.bdui.core.domain.widget.TextWidget
import com.app.bdui.core.domain.widget.Widget
import com.app.bdui.core.domain.widget.WidgetType
import kotlinx.serialization.json.boolean
import kotlinx.serialization.json.booleanOrNull
import kotlinx.serialization.json.int
import kotlinx.serialization.json.intOrNull
import java.util.UUID
import kotlin.collections.mapValues

internal fun ScreenDto.toDomain(): Screen {
    return Screen(
        state = state.mapValues { (_, value) ->
            when {
                value.isString -> StringValue(value.content)
                value.booleanOrNull != null -> BooleanValue(value.boolean)
                value.intOrNull != null -> IntegerValue(value.int)
                else -> error("Invalid value: $value")
            }
        },
        content = content.map(WidgetDto::toDomain),
    )
}

internal fun WidgetDto.toDomain(): Widget {
    return when (WidgetType.of(type)) {
        WidgetType.ROW -> RowWidget(
            id = id ?: UUID.randomUUID().toString(),
            modifier = modifier.orEmpty().map(ModifierDto::toDomain),
            children = children.orEmpty().map(WidgetDto::toDomain),
        )
        WidgetType.COLUMN -> ColumnWidget(
            id = id ?: UUID.randomUUID().toString(),
            modifier = modifier.orEmpty().map(ModifierDto::toDomain),
            children = children.orEmpty().map(WidgetDto::toDomain),
        )
        WidgetType.BOX -> BoxWidget(
            id = id ?: UUID.randomUUID().toString(),
            modifier = modifier.orEmpty().map(ModifierDto::toDomain),
            children = children.orEmpty().map(WidgetDto::toDomain),
        )
        WidgetType.TEXT -> TextWidget(
            id = id ?: UUID.randomUUID().toString(),
            text = params?.text?.toDomain() ?: Literal(""),
            modifier = modifier.orEmpty().map(ModifierDto::toDomain),
        )
        WidgetType.BUTTON -> ButtonWidget(
            id = id ?: UUID.randomUUID().toString(),
            modifier = modifier.orEmpty().map(ModifierDto::toDomain),
            text = params?.text?.toDomain() ?: Literal(""),
            enabled = params?.enabled?.toDomain() ?: Literal(true),
            onClick = params?.onClick.orEmpty().map(ActionDto::toDomain)
        )
        WidgetType.TEXT_FIELD -> TextFieldWidget(
            id = id ?: UUID.randomUUID().toString(),
            modifier = modifier.orEmpty().map(ModifierDto::toDomain),
            text = params?.text?.toDomain() ?: Literal(""),
            enabled = params?.enabled?.toDomain() ?: Literal(true),
        )
    }
}