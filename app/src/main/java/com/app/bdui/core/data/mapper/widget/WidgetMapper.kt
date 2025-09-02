package com.app.bdui.core.data.mapper.widget

import com.app.bdui.core.data.mapper.action.toDomain
import com.app.bdui.core.data.mapper.evaluation.toDomain
import com.app.bdui.core.data.mapper.modifier.base.toDomain
import com.app.bdui.core.data.mapper.value.toDynamicValue
import com.app.bdui.core.data.network.action.ActionDto
import com.app.bdui.core.data.network.modifier.base.ModifierDto
import com.app.bdui.core.data.network.widget.ScreenDto
import com.app.bdui.core.data.network.widget.WidgetDto
import com.app.bdui.core.domain.entity.Screen
import com.app.bdui.core.domain.entity.EvalContext
import com.app.bdui.core.domain.evaluation.Literal
import com.app.bdui.core.domain.widget.BoxWidget
import com.app.bdui.core.domain.widget.ButtonWidget
import com.app.bdui.core.domain.widget.ColumnWidget
import com.app.bdui.core.domain.widget.RowWidget
import com.app.bdui.core.domain.widget.TemplateWidget
import com.app.bdui.core.domain.widget.TextFieldWidget
import com.app.bdui.core.domain.widget.TextWidget
import com.app.bdui.core.domain.widget.Widget
import com.app.bdui.core.domain.widget.WidgetType
import java.util.UUID
import kotlin.collections.mapValues

internal fun ScreenDto.toDomain(): Screen {
    return Screen(
        state = state.orEmpty().mapValues { (_, value) -> value.toDynamicValue() },
        templates = templates.orEmpty().mapValues { (_, value) -> value.toDomain() },
        content = content.toDomain(),
    )
}

internal fun WidgetDto.toDomain(): Widget {
    return when (WidgetType.of(type)) {
        WidgetType.TEMPLATE -> TemplateWidget(
            id = id ?: UUID.randomUUID().toString(),
            name = name ?: error("Can't create template without a name"),
            state = EvalContext(
                initial = state.orEmpty().mapValues { (_, value) -> value.toDynamicValue() }
            ),
        )

        WidgetType.ROW -> RowWidget(
            id = id ?: UUID.randomUUID().toString(),
            children = children.orEmpty().map(WidgetDto::toDomain),
            modifier = modifier.orEmpty().map(ModifierDto::toDomain),
        )

        WidgetType.COLUMN -> ColumnWidget(
            id = id ?: UUID.randomUUID().toString(),
            children = children.orEmpty().map(WidgetDto::toDomain),
            modifier = modifier.orEmpty().map(ModifierDto::toDomain),
        )

        WidgetType.BOX -> BoxWidget(
            id = id ?: UUID.randomUUID().toString(),
            children = children.orEmpty().map(WidgetDto::toDomain),
            modifier = modifier.orEmpty().map(ModifierDto::toDomain),
        )

        WidgetType.TEXT -> TextWidget(
            id = id ?: UUID.randomUUID().toString(),
            text = params?.text?.toDomain() ?: Literal(""),
            modifier = modifier.orEmpty().map(ModifierDto::toDomain),
        )

        WidgetType.BUTTON -> ButtonWidget(
            id = id ?: UUID.randomUUID().toString(),
            text = params?.text?.toDomain() ?: Literal(""),
            enabled = params?.enabled?.toDomain() ?: Literal(true),
            onClick = params?.onClick.orEmpty().map(ActionDto::toDomain),
            modifier = modifier.orEmpty().map(ModifierDto::toDomain),
        )

        WidgetType.TEXT_FIELD -> TextFieldWidget(
            id = id ?: UUID.randomUUID().toString(),
            text = params?.text?.toDomain() ?: Literal(""),
            enabled = params?.enabled?.toDomain() ?: Literal(true),
            modifier = modifier.orEmpty().map(ModifierDto::toDomain),
        )
    }
}