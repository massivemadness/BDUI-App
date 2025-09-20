package com.app.bdui.core.data.mapper.modifier

import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.core.graphics.toColorInt
import com.app.bdui.core.domain.modifier.ModifierFactory
import com.app.bdui.core.data.network.modifier.ModifierDto
import com.app.bdui.core.data.network.modifier.ModifierParamsDto
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
    checkNotNull(params) { "Modifier must have params" }
    return when (ModifierType.of(type)) {
        ModifierType.SIZE -> mapSize(params)
        ModifierType.FILL -> mapFillSize(params)
        ModifierType.WRAP -> mapWrapSize(params)
        ModifierType.PADDING -> mapPadding(params)
        ModifierType.BACKGROUND -> mapBackground(params)
        ModifierType.CORNERS -> mapCorners(params)
        ModifierType.ALIGN -> mapAlign(params)
        ModifierType.WEIGHT -> mapWeight(params)
        ModifierType.UNKNOWN -> UnknownModifier(type)
    }
}

private fun mapSize(params: ModifierParamsDto): SizeModifier {
    return when {
        params.size != null -> SizeModifier(
            width = params.size,
            height = params.size,
        )
        else -> SizeModifier(
            width = params.width ?: 0,
            height = params.height ?: 0
        )
    }
}

private fun mapFillSize(params: ModifierParamsDto): FillSizeModifier {
    return FillSizeModifier(
        fillMode = FillMode.of(params.fill),
        fraction = params.fraction ?: 1f,
    )
}

private fun mapWrapSize(params: ModifierParamsDto): WrapSizeModifier {
    return WrapSizeModifier(
        wrapMode = WrapMode.of(params.wrap),
    )
}

private fun mapPadding(params: ModifierParamsDto): PaddingModifier {
    return when {
        params.all != null -> PaddingModifier(
            start = params.all,
            top = params.all,
            end = params.all,
            bottom = params.all
        )
        params.horizontal != null || params.vertical != null -> PaddingModifier(
            start = params.horizontal ?: params.start ?: 0,
            top = params.vertical ?: params.top ?: 0,
            end = params.horizontal ?: params.end ?: 0,
            bottom = params.vertical ?: params.bottom ?: 0,
        )
        else -> PaddingModifier(
            start = params.start ?: 0,
            top = params.top ?: 0,
            end = params.end ?: 0,
            bottom = params.bottom ?: 0
        )
    }
}

private fun mapBackground(params: ModifierParamsDto): BackgroundModifier {
    val color = if (params.color != null) {
        Color(params.color.toColorInt())
    } else {
        Color.Unspecified
    }
    return BackgroundModifier(color)
}

private fun mapCorners(params: ModifierParamsDto): CornersModifier {
    return when {
        params.all != null -> CornersModifier(
            topStart = params.all,
            topEnd = params.all,
            bottomStart = params.all,
            bottomEnd = params.all,
        )
        else -> CornersModifier(
            topStart = params.topStart ?: 0,
            topEnd = params.topEnd ?: 0,
            bottomStart = params.bottomStart ?: 0,
            bottomEnd = params.bottomEnd ?: 0,
        )
    }
}

private fun mapAlign(params: ModifierParamsDto): AlignModifier {
    return AlignModifier(
        alignment = when (BduiAlignment.of(params.alignment)) {
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
        horizontalAlignment = when (BduiAlignment.of(params.alignment)) {
            BduiAlignment.Start -> Alignment.Start
            BduiAlignment.Center -> Alignment.CenterHorizontally
            BduiAlignment.End -> Alignment.End
            else -> null
        },
        verticalAlignment = when (BduiAlignment.of(params.alignment)) {
            BduiAlignment.Top -> Alignment.Top
            BduiAlignment.Center -> Alignment.CenterVertically
            BduiAlignment.Bottom -> Alignment.Bottom
            else -> null
        }
    )
}

private fun mapWeight(params: ModifierParamsDto): WeightModifier {
    return WeightModifier(weight = params.weight ?: 1f)
}