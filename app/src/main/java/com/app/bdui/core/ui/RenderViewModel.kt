package com.app.bdui.core.ui

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.bdui.core.domain.action.Action
import com.app.bdui.core.domain.action.GoBackAction
import com.app.bdui.core.domain.action.NavigateAction
import com.app.bdui.core.domain.action.PushStateAction
import com.app.bdui.core.domain.action.SyncStateAction
import com.app.bdui.core.domain.action.SnackbarAction
import com.app.bdui.core.domain.evaluation.EvalContext
import com.app.bdui.core.domain.evaluation.Reference
import com.app.bdui.core.domain.repository.WidgetsRepository
import com.app.bdui.core.domain.widget.BoxWidget
import com.app.bdui.core.domain.widget.ButtonWidget
import com.app.bdui.core.domain.widget.ColumnWidget
import com.app.bdui.core.domain.widget.RowWidget
import com.app.bdui.core.domain.widget.TextFieldWidget
import com.app.bdui.core.domain.widget.TextWidget
import com.app.bdui.core.domain.widget.Widget
import com.app.bdui.core.ui.extensions.evalBoolean
import com.app.bdui.core.ui.extensions.evalString
import com.app.bdui.core.ui.widget.BoxWidgetNode
import com.app.bdui.core.ui.widget.ButtonWidgetNode
import com.app.bdui.core.ui.widget.ColumnWidgetNode
import com.app.bdui.core.ui.widget.RowWidgetNode
import com.app.bdui.core.ui.widget.TextFieldWidgetNode
import com.app.bdui.core.ui.widget.TextWidgetNode
import com.app.bdui.core.ui.widget.WidgetNode
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flatMapConcat
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.launch

internal class RenderViewModel(
    private val widgetsRepository: WidgetsRepository,
) : ViewModel() {

    private val _widgets = MutableStateFlow<List<WidgetNode>>(emptyList())
    val widgets = _widgets.asStateFlow()

    init {
        viewModelScope.launch {
            try {
                // TODO loading state

                val screen = widgetsRepository.loadScreen(screenId = "1")
                val context = EvalContext(screen.state)

                _widgets.value = screen.content.map { buildWidgetTree(it, context) }
            } catch (e: Exception) {
                Log.e("RenderViewModel", e.message, e)
                // TODO error state
            }
        }
    }

    private fun buildWidgetTree(widget: Widget, ctx: EvalContext): WidgetNode {
        return when (widget) {
            is RowWidget -> RowWidgetNode(
                id = widget.id,
                modifier = widget.modifier,
                children = widget.children.map { buildWidgetTree(it, ctx) }
            )

            is ColumnWidget -> ColumnWidgetNode(
                id = widget.id,
                modifier = widget.modifier,
                children = widget.children.map { buildWidgetTree(it, ctx) }
            )

            is BoxWidget -> BoxWidgetNode(
                id = widget.id,
                modifier = widget.modifier,
                children = widget.children.map { buildWidgetTree(it, ctx) }
            )

            is ButtonWidget -> ButtonWidgetNode(
                id = widget.id,
                modifier = widget.modifier,
                text = widget.text.evalString(ctx, viewModelScope),
                enabled = widget.enabled.evalBoolean(ctx, viewModelScope),
                onClick = { handleAction(widget.onClick) }
            )

            is TextFieldWidget -> TextFieldWidgetNode(
                id = widget.id,
                modifier = widget.modifier,
                text = widget.text.evalString(ctx, viewModelScope),
                enabled = widget.enabled.evalBoolean(ctx, viewModelScope),
                onTextChanged = { value ->
                    if (widget.text is Reference) {
                        ctx.set(widget.text.ref, value)
                    }
                }
            )

            is TextWidget -> TextWidgetNode(
                id = widget.id,
                modifier = widget.modifier,
                text = widget.text.evalString(ctx, viewModelScope),
            )
        }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    private fun handleAction(actions: List<Action>) {
        // TODO
        /*flowOf(*actions.toTypedArray())
            .flatMapConcat { action ->
                when (action) {
                    is GoBackAction -> {
                        flowOf(action)
                    }

                    is NavigateAction -> {
                        flowOf(action)
                    }

                    is PushStateAction -> {
                        flowOf(action)
                    }

                    is SyncStateAction -> {
                        flowOf(action)
                    }

                    is SnackbarAction -> {
                        flowOf(action)
                    }
                }
            }
            .launchIn(viewModelScope)*/
    }
}