package com.app.bdui.core.ui

import android.util.Log
import androidx.compose.runtime.Immutable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.bdui.core.domain.action.Action
import com.app.bdui.core.domain.action.GoBackAction
import com.app.bdui.core.domain.action.NavigateAction
import com.app.bdui.core.domain.action.PushStateAction
import com.app.bdui.core.domain.action.SnackbarAction
import com.app.bdui.core.domain.action.SyncStateAction
import com.app.bdui.core.domain.entity.EvalContext
import com.app.bdui.core.domain.evaluation.Reference
import com.app.bdui.core.domain.repository.WidgetsRepository
import com.app.bdui.core.domain.widget.BoxWidget
import com.app.bdui.core.domain.widget.ButtonWidget
import com.app.bdui.core.domain.widget.ColumnWidget
import com.app.bdui.core.domain.widget.ConditionalWidget
import com.app.bdui.core.domain.widget.RowWidget
import com.app.bdui.core.domain.widget.ScrollWidget
import com.app.bdui.core.domain.widget.SpacerWidget
import com.app.bdui.core.domain.widget.TemplateWidget
import com.app.bdui.core.domain.widget.TextFieldWidget
import com.app.bdui.core.domain.widget.TextWidget
import com.app.bdui.core.domain.widget.Widget
import com.app.bdui.core.ui.extensions.evalBoolean
import com.app.bdui.core.ui.extensions.evalString
import com.app.bdui.core.ui.widget.BoxWidgetNode
import com.app.bdui.core.ui.widget.ButtonWidgetNode
import com.app.bdui.core.ui.widget.ColumnWidgetNode
import com.app.bdui.core.ui.widget.ConditionalWidgetNode
import com.app.bdui.core.ui.widget.RowWidgetNode
import com.app.bdui.core.ui.widget.ScrollWidgetNode
import com.app.bdui.core.ui.widget.SpacerWidgetNode
import com.app.bdui.core.ui.widget.TextFieldWidgetNode
import com.app.bdui.core.ui.widget.TextWidgetNode
import com.app.bdui.core.ui.widget.WidgetNode
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

internal class RenderViewModel(
    private val widgetsRepository: WidgetsRepository,
) : ViewModel() {

    private val _viewState = MutableStateFlow<ViewState>(ViewState())
    val viewState = _viewState.asStateFlow()

    private val _viewEvent = Channel<ViewEvent>(Channel.BUFFERED)
    val viewEvent: Flow<ViewEvent> = _viewEvent.receiveAsFlow()

    private val screenId: String = "123"

    init {
        viewModelScope.launch {
            try {
                _viewState.update {
                    it.copy(
                        isLoading = true,
                        isError = false,
                    )
                }

                val screen = widgetsRepository.loadScreen(screenId)
                val context = EvalContext(screen.state)
                val widget = buildWidgetTree(
                    widget = screen.content,
                    templates = screen.templates,
                    ctx = context,
                )

                _viewState.value = ViewState(
                    widget = widget,
                    isLoading = false,
                    isError = false,
                )
            } catch (e: Exception) {
                Log.e("RenderViewModel", e.message, e)
                _viewState.update {
                    it.copy(
                        isLoading = false,
                        isError = true,
                    )
                }
            }
        }
    }

    private fun buildWidgetTree(
        widget: Widget,
        templates: Map<String, Widget>,
        ctx: EvalContext,
    ): WidgetNode {
        return when (widget) {
            is TemplateWidget -> buildWidgetTree(
                widget = templates[widget.name] ?: error("Can't create template without a name"),
                templates = templates,
                ctx = widget.state,
            )

            is ConditionalWidget -> ConditionalWidgetNode(
                id = widget.id,
                modifier = widget.modifier,
                visible = widget.condition.evalBoolean(ctx, viewModelScope),
                children = widget.children.map { buildWidgetTree(it, templates, ctx) }
            )

            is RowWidget -> RowWidgetNode(
                id = widget.id,
                modifier = widget.modifier,
                children = widget.children.map { buildWidgetTree(it, templates, ctx) }
            )

            is ColumnWidget -> ColumnWidgetNode(
                id = widget.id,
                modifier = widget.modifier,
                children = widget.children.map { buildWidgetTree(it, templates, ctx) }
            )

            is BoxWidget -> BoxWidgetNode(
                id = widget.id,
                modifier = widget.modifier,
                children = widget.children.map { buildWidgetTree(it, templates, ctx) }
            )

            is ScrollWidget -> ScrollWidgetNode(
                id = widget.id,
                modifier = widget.modifier,
                axis = widget.axis,
                children = widget.children.map { buildWidgetTree(it, templates, ctx) }
            )

            is ButtonWidget -> ButtonWidgetNode(
                id = widget.id,
                modifier = widget.modifier,
                text = widget.text.evalString(ctx, viewModelScope),
                enabled = widget.enabled.evalBoolean(ctx, viewModelScope),
                onClick = { handleActions(widget.onClick, ctx) }
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

            is SpacerWidget -> SpacerWidgetNode(
                id = widget.id,
                modifier = widget.modifier,
            )
        }
    }

    private fun handleActions(actions: List<Action>, ctx: EvalContext) {
        viewModelScope.launch {
            actions.forEach { action ->
                handleAction(action, ctx)
            }
        }
    }

    private suspend fun handleAction(action: Action, ctx: EvalContext) {
        when (action) {
            is PushStateAction -> {
                ctx.set(action.ref, action.value.eval(ctx).first())
            }
            is SyncStateAction -> {
                widgetsRepository.loadActions(
                    screenId = screenId,
                    actionId = action.actionId,
                    snapshot = ctx.snapshot()
                ).forEach { action ->
                    handleAction(action, ctx)
                }
            }
            is NavigateAction -> {
                _viewEvent.send(ViewEvent.Navigate(action.deeplink))
            }
            is GoBackAction -> {
                _viewEvent.send(ViewEvent.PopBackStack)
            }
            is SnackbarAction -> {
                _viewEvent.send(ViewEvent.ShowSnackbar(action.message))
            }
            else -> {
                Log.e("RenderViewModel", "Unknown action")
            }
        }
    }

    @Immutable
    data class ViewState(
        val widget: WidgetNode? = null,
        val isLoading: Boolean = true,
        val isError: Boolean = false,
    )

    sealed interface ViewEvent {
        data class Navigate(val deeplink: String) : ViewEvent
        data object PopBackStack : ViewEvent
        data class ShowSnackbar(val message: String): ViewEvent
    }
}