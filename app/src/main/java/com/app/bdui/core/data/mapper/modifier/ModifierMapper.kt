package com.app.bdui.core.data.mapper.modifier

import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.core.graphics.toColorInt
import com.app.bdui.core.domain.modifier.ModifierFactory
import com.app.bdui.core.data.network.modifier.ModifierDto
import com.app.bdui.core.data.network.modifier.ModifierValueDto
import com.app.bdui.core.domain.modifier.AlignModifier
import com.app.bdui.core.domain.modifier.BackgroundModifier
import com.app.bdui.core.domain.modifier.CornersModifier
import com.app.bdui.core.domain.modifier.FillSizeModifier
import com.app.bdui.core.domain.modifier.ModifierType
import com.app.bdui.core.domain.modifier.PaddingModifier
import com.app.bdui.core.domain.modifier.SizeModifier
import com.app.bdui.core.domain.modifier.UnknownModifier
import com.app.bdui.core.domain.modifier.WeightModifier
import com.app.bdui.core.domain.modifier.WrapSizeModifier
import com.app.bdui.core.domain.entity.BduiAlignment
import com.app.bdui.core.domain.entity.FillMode
import com.app.bdui.core.domain.entity.WrapMode

internal fun ModifierDto.toDomain(): ModifierFactory {
    checkNotNull(value) { "Modifier must have value" }
    return when (ModifierType.of(type)) {
        ModifierType.SIZE -> mapSize(value)
        ModifierType.FILL -> mapFillSize(value)
        ModifierType.WRAP -> mapWrapSize(value)
        ModifierType.PADDING -> mapPadding(value)
        ModifierType.BACKGROUND -> mapBackground(value)
        ModifierType.CORNERS -> mapCorners(value)
        ModifierType.ALIGN -> mapAlign(value)
        ModifierType.WEIGHT -> mapWeight(value)
        ModifierType.UNKNOWN -> UnknownModifier(type)
    }
}

private fun mapSize(value: ModifierValueDto): SizeModifier {
    return when {
        value.size != null -> SizeModifier(
            width = value.size,
            height = value.size,
        )
        else -> SizeModifier(
            width = value.width ?: 0,
            height = value.height ?: 0
        )
    }
}

private fun mapFillSize(value: ModifierValueDto): FillSizeModifier {
    return FillSizeModifier(
        fillMode = FillMode.of(value.fill),
        fraction = value.fraction ?: 1f,
    )
}

private fun mapWrapSize(value: ModifierValueDto): WrapSizeModifier {
    return WrapSizeModifier(
        wrapMode = WrapMode.of(value.wrap),
    )
}

private fun mapPadding(value: ModifierValueDto): PaddingModifier {
    return when {
        value.all != null -> PaddingModifier(
            start = value.all,
            top = value.all,
            end = value.all,
            bottom = value.all
        )
        value.horizontal != null || value.vertical != null -> PaddingModifier(
            start = value.horizontal ?: value.start ?: 0,
            top = value.vertical ?: value.top ?: 0,
            end = value.horizontal ?: value.end ?: 0,
            bottom = value.vertical ?: value.bottom ?: 0,
        )
        else -> PaddingModifier(
            start = value.start ?: 0,
            top = value.top ?: 0,
            end = value.end ?: 0,
            bottom = value.bottom ?: 0
        )
    }
}

private fun mapBackground(value: ModifierValueDto): BackgroundModifier {
    val color = if (value.color != null) {
        Color(value.color.toColorInt())
    } else {
        Color.Unspecified
    }
    return BackgroundModifier(color)
}

private fun mapCorners(value: ModifierValueDto): CornersModifier {
    return when {
        value.all != null -> CornersModifier(
            topStart = value.all,
            topEnd = value.all,
            bottomStart = value.all,
            bottomEnd = value.all,
        )
        else -> CornersModifier(
            topStart = value.topStart ?: 0,
            topEnd = value.topEnd ?: 0,
            bottomStart = value.bottomStart ?: 0,
            bottomEnd = value.bottomEnd ?: 0,
        )
    }
}

private fun mapAlign(value: ModifierValueDto): AlignModifier {
    return AlignModifier(
        alignment = when (BduiAlignment.of(value.alignment)) {
            BduiAlignment.TopStart -> Alignment.TopStart
            BduiAlignment.TopCenter -> Alignment.TopCenter
            BduiAlignment.TopEnd -> Alignment.TopEnd
            BduiAlignment.CenterStart -> Alignment.CenterStart
            BduiAlignment.Center -> Alignment.Center
            BduiAlignment.CenterEnd -> Alignment.CenterEnd
            BduiAlignment.BottomStart -> Alignment.BottomStart
            BduiAlignment.BottomCenter -> Alignment.BottomCenter
            BduiAlignment.BottomEnd -> Alignment.BottomEnd
            else -> null
        },
        horizontalAlignment = when (BduiAlignment.of(value.alignment)) {
            BduiAlignment.Start -> Alignment.Start
            BduiAlignment.Center -> Alignment.CenterHorizontally
            BduiAlignment.End -> Alignment.End
            else -> null
        },
        verticalAlignment = when (BduiAlignment.of(value.alignment)) {
            BduiAlignment.Top -> Alignment.Top
            BduiAlignment.Center -> Alignment.CenterVertically
            BduiAlignment.Bottom -> Alignment.Bottom
            else -> null
        }
    )
}

private fun mapWeight(value: ModifierValueDto): WeightModifier {
    return WeightModifier(weight = value.weight ?: 1f)
}