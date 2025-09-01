package com.app.bdui

import com.app.bdui.core.domain.entity.BooleanValue
import com.app.bdui.core.domain.evaluation.And
import com.app.bdui.core.domain.evaluation.Empty
import com.app.bdui.core.domain.evaluation.EvalContext
import com.app.bdui.core.domain.evaluation.Literal
import com.app.bdui.core.domain.evaluation.Or
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Test

class AndEvaluationTest {

    private val ctx = EvalContext(emptyMap())

    @Test
    fun `When any item is false Then return false`() = runTest {
        val evaluation = And(
            listOf(
                Literal(true),
                Literal(true),
                Literal(false)
            )
        )

        val result = evaluation.eval(ctx).first()

        assertEquals(BooleanValue(false), result)
    }

    @Test
    fun `When all items is true Then return true`() = runTest {
        val evaluation = And(
            listOf(
                Literal(true),
                Literal(true),
                Literal(true)
            )
        )

        val result = evaluation.eval(ctx).first()

        assertEquals(BooleanValue(true), result)
    }

    @Test
    fun `When all items is false Then return false`() = runTest {
        val evaluation = And(
            listOf(
                Literal(false),
                Literal(false),
                Literal(false)
            )
        )

        val result = evaluation.eval(ctx).first()

        assertEquals(BooleanValue(false), result)
    }
}