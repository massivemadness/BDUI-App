package com.app.bdui

import com.app.bdui.core.domain.entity.EvalContext
import com.app.bdui.core.domain.evaluation.Empty
import com.app.bdui.core.domain.evaluation.Equals
import com.app.bdui.core.domain.evaluation.IfElse
import com.app.bdui.core.domain.evaluation.Length
import com.app.bdui.core.domain.evaluation.Literal
import com.app.bdui.core.domain.value.StringValue
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Test

class IfElseEvaluationTest {

    private val ctx = EvalContext(emptyMap())

    @Test
    fun `When condition is true Then branch is evaluated`() = runTest {
        val evaluation = IfElse(
            condition = Equals(
                left = Length(target = Literal("Hello World!")),
                right = Literal(12),
            ),
            thenBranch = Literal("then branch"),
            elseBranch = Literal("else branch"),
        )

        val result = evaluation.eval(ctx).first()

        assertEquals(StringValue("then branch"), result)
    }

    @Test
    fun `When condition is false Then branch is evaluated`() = runTest {
        val evaluation = IfElse(
            condition = Empty(target = Literal("empty string")),
            thenBranch = Literal("then branch"),
            elseBranch = Literal("else branch"),
        )

        val result = evaluation.eval(ctx).first()

        assertEquals(StringValue("else branch"), result)
    }
}