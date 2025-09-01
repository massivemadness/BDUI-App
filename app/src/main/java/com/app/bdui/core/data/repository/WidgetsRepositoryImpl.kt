package com.app.bdui.core.data.repository

import com.app.bdui.core.data.mapper.widget.toDomain
import com.app.bdui.core.data.network.widget.ScreenDto
import com.app.bdui.core.domain.action.Action
import com.app.bdui.core.domain.action.PushStateAction
import com.app.bdui.core.domain.action.SnackbarAction
import com.app.bdui.core.domain.entity.Screen
import com.app.bdui.core.domain.entity.StringValue
import com.app.bdui.core.domain.repository.WidgetsRepository
import kotlinx.coroutines.delay
import kotlinx.serialization.json.Json

internal class WidgetsRepositoryImpl : WidgetsRepository {

    override suspend fun loadScreen(screenId: String): Screen {
        val json = """
            {
              "state": {
                "text_field.name": "",
                "text_field.phone": "+",
                "submit.loading": false
              },
              "content": [
                {
                  "type": "column",
                  "modifier": [ 
                      { "fill": "max_size" },
                      { "padding": { "top": 72 } }
                  ],
                  "children": [
                    {
                      "type": "textfield",
                      "params": {
                        "text": "$.text_field.name",
                        "enabled": true
                      },
                      "modifier": [
                        { "fill": "max_width" },
                        { "padding": 16 }
                      ]
                    },
                    {
                      "type": "textfield",
                      "params": {
                        "text": "$.text_field.phone",
                        "enabled": true
                      },
                      "modifier": [
                        { "fill": "max_width" },
                        { "padding": 16 }
                      ]
                    },
                    {
                      "id": "button_submit",
                      "type": "button",
                      "params": {
                        "text": "Submit",
                        "enabled": {
                          "and": [
                            { "not": "$.submit.loading" },
                            { "notEmpty": "$.text_field.name" },
                            {
                              "or": [
                                { "equals": [ { "length": "$.text_field.phone" }, 10 ] },
                                { "equals": [ { "length": "$.text_field.phone" }, 12 ] }
                              ]
                            }
                          ]    
                        },
                        "onClick": [
                          {
                            "type": "push_state",
                            "ref": "submit.loading",
                            "value": true
                          },
                          {
                            "type": "sync_state",
                            "widget_id": "button_submit"
                          },
                          {
                            "type": "push_state",
                            "ref": "submit.loading",
                            "value": false
                          }
                        ]   
                      },
                      "modifier": [
                        { "fill": "max_width" },
                        { "padding": 16 }
                      ]
                    }
                  ]
                }
              ]
            }
        """.trimIndent()

        val dto = Json.decodeFromString<ScreenDto>(json)
        return dto.toDomain()
    }

    override suspend fun dispatchAction(action: Action): List<Action> {
        delay(1000L)
        return listOf(
            PushStateAction("text_field.name", StringValue("")),
            PushStateAction("text_field.phone", StringValue("")),
            SnackbarAction("Success"),
        )
    }
}