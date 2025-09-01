package com.app.bdui.core.data.mapper.evaluation

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
import com.app.bdui.core.domain.entity.BooleanValue
import com.app.bdui.core.domain.entity.IntegerValue
import com.app.bdui.core.domain.entity.StringValue
import com.app.bdui.core.domain.evaluation.Not
import kotlinx.serialization.json.boolean
import kotlinx.serialization.json.booleanOrNull
import kotlinx.serialization.json.int
import kotlinx.serialization.json.intOrNull

private const val REF_PREFIX = "$."

internal fun EvaluationDto.toDomain(): Evaluation = when {
    reference != null -> Reference(reference)

    literal != null -> when {
        literal.isString -> {
            val content = literal.content
            if (content.startsWith(REF_PREFIX)) {
                Reference(content.substringAfter(REF_PREFIX))
            } else {
                Literal(StringValue(content))
            }
        }
        literal.booleanOrNull != null -> Literal(BooleanValue(literal.boolean))
        literal.intOrNull != null -> Literal(IntegerValue(literal.int))
        else -> error("Invalid type: $literal")
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

    else -> error("Invalid expression: $this")
}