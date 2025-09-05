package com.app.bdui.core.data.repository

import com.app.bdui.core.data.mapper.action.toDomain
import com.app.bdui.core.data.mapper.widget.toDomain
import com.app.bdui.core.data.network.action.ActionDto
import com.app.bdui.core.data.network.widget.ScreenDto
import com.app.bdui.core.domain.action.Action
import com.app.bdui.core.domain.action.PushStateAction
import com.app.bdui.core.domain.action.SnackbarAction
import com.app.bdui.core.domain.action.SyncStateAction
import com.app.bdui.core.domain.entity.Screen
import com.app.bdui.core.domain.value.StringValue
import com.app.bdui.core.domain.entity.EvalContext
import com.app.bdui.core.domain.entity.Snapshot
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
              "templates": {
                "example_template": {
                  "type": "column",
                  "modifier": [
                    { "fill": "max_width" },
                    { "padding": { "horizontal": 16, "vertical": 12 } }
                  ],
                  "children": [
                    {
                      "type": "text",
                      "params": {
                        "text": "$.title"
                      },
                      "modifier": [
                        { "fill": "max_width" }
                      ]
                    },
                    {
                      "type": "text",
                      "params": {
                        "text": "$.subtitle"
                      },
                      "modifier": [
                        { "fill": "max_width" }
                      ]
                    }
                  ]
                }
              },
              "content": {
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
                      "text": {
                        "if": { "notEmpty": "$.text_field.name" },
                        "then": "Submit",
                        "else": "..."
                      },
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
                          "id": "action_button_submit"
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
                  },
                  {
                    "type": "template",
                    "name": "example_template",
                    "state": {
                      "title": "Title",
                      "subtitle": "Subtitle"
                    }
                  },
                  {
                    "type": "template",
                    "name": "example_template",
                    "state": {
                      "title": "Hello world!",
                      "subtitle": "Hello template!"
                    }
                  }
                ]
              }
            }
        """.trimIndent()

        val jsonParser = Json { ignoreUnknownKeys = true }
        val dto = jsonParser.decodeFromString<ScreenDto>(json)

        return dto.toDomain()
    }

    override suspend fun loadActions(
        screenId: String,
        actionId: String,
        snapshot: Snapshot
    ): List<Action> {
        delay(1000L)

        val json = """
            [
              {
                "type": "push_state",
                "ref": "text_field.name",
                "value": ""
              },
              {
                "type": "push_state",
                "ref": "text_field.phone",
                "value": ""
              },
              {
                "type": "show_snackbar",
                "message": "Success"
              }
            ]
        """.trimIndent()

        val jsonParser = Json { ignoreUnknownKeys = true }
        val dto = jsonParser.decodeFromString<List<ActionDto>>(json)

        return dto.map(ActionDto::toDomain)
    }
}