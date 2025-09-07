package com.app.bdui.core.data.mapper.evaluation

import com.app.bdui.core.data.mapper.value.toDynamicValue
import com.app.bdui.core.domain.evaluation.And
import com.app.bdui.core.domain.evaluation.Empty
import com.app.bdui.core.domain.evaluation.Equals
import com.app.bdui.core.domain.evaluation.Evaluation
import com.app.bdui.core.domain.evaluation.Reference
import com.app.bdui.core.domain.evaluation.Length
import com.app.bdui.core.domain.evaluation.Literal
import com.app.bdui.core.domain.evaluation.NotEmpty
import com.app.bdui.core.domain.evaluation.Or
import com.app.bdui.core.data.network.evaluation.EvaluationDto
import com.app.bdui.core.domain.evaluation.IfElse
import com.app.bdui.core.domain.value.BooleanValue
import com.app.bdui.core.domain.value.IntegerValue
import com.app.bdui.core.domain.value.StringValue
import com.app.bdui.core.domain.evaluation.Not
import com.app.bdui.core.domain.value.ArrayValue
import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.JsonPrimitive
import kotlinx.serialization.json.boolean
import kotlinx.serialization.json.booleanOrNull
import kotlinx.serialization.json.int
import kotlinx.serialization.json.intOrNull

private const val REF_PREFIX = "$."

internal fun EvaluationDto.toDomain(): Evaluation = when {
    reference != null -> Reference(reference)

    literal != null -> when (literal) {
        is JsonPrimitive -> when {
            literal.isString -> {
                val content = literal.content
                if (content.startsWith(REF_PREFIX)) {
                    Reference(content.substringAfter(REF_PREFIX))
                } else {
                    Literal(literal.toDynamicValue())
                }
            }
            literal.booleanOrNull != null -> Literal(literal.toDynamicValue())
            literal.intOrNull != null -> Literal(literal.toDynamicValue())
            else -> error("Invalid type: $literal")
        }
        is JsonArray -> Literal(literal.toDynamicValue())
        is JsonObject -> error("Invalid type: $literal")
    }

    empty != null -> Empty(empty.toDomain())
    notEmpty != null -> NotEmpty(notEmpty.toDomain())
    length != null -> Length(length.toDomain())

    equals != null -> {
        require(equals.size == 2)
        Equals(equals[0].toDomain(), equals[1].toDomain())
    }

    or != null -> Or(or.map(EvaluationDto::toDomain))
    and != null -> And(and.map(EvaluationDto::toDomain))
    not != null -> Not(not.toDomain())
    condition != null -> IfElse(
        condition = condition.toDomain(),
        thenBranch = thenBranch?.toDomain() ?: error("Must specify then branch"),
        elseBranch = elseBranch?.toDomain() ?: error("Must specify else branch"),
    )

    else -> error("Invalid expression: $this")
}