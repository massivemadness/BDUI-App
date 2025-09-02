package com.app.bdui

import com.app.bdui.core.domain.value.BooleanValue
import com.app.bdui.core.domain.evaluation.Equals
import com.app.bdui.core.domain.entity.EvalContext
import com.app.bdui.core.domain.evaluation.Literal
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Test

class EqualsEvaluationTest {

    private val ctx = EvalContext(emptyMap())

    @Test
    fun `When both strings are equal Then return true`() = runTest {
        val evaluation = Equals(
            left = Literal("hello"),
            right = Literal("hello"),
        )

        val result = evaluation.eval(ctx).first()

        assertEquals(BooleanValue(true), result)
    }

    @Test
    fun `When both strings are not equal Then return false`() = runTest {
        val evaluation = Equals(
            left = Literal("hello"),
            right = Literal("world"),
        )

        val result = evaluation.eval(ctx).first()

        assertEquals(BooleanValue(false), result)
    }

    @Test
    fun `When both integers are equal Then return true`() = runTest {
        val evaluation = Equals(
            left = Literal(12),
            right = Literal(12),
        )

        val result = evaluation.eval(ctx).first()

        assertEquals(BooleanValue(true), result)
    }

    @Test
    fun `When both integers are not equal Then return false`() = runTest {
        val evaluation = Equals(
            left = Literal(10),
            right = Literal(98),
        )

        val result = evaluation.eval(ctx).first()

        assertEquals(BooleanValue(false), result)
    }

    @Test
    fun `When both booleans are equal Then return true`() = runTest {
        val evaluation = Equals(
            left = Literal(false),
            right = Literal(false),
        )

        val result = evaluation.eval(ctx).first()

        assertEquals(BooleanValue(true), result)
    }

    @Test
    fun `When both booleans are not equal Then return false`() = runTest {
        val evaluation = Equals(
            left = Literal(true),
            right = Literal(false),
        )

        val result = evaluation.eval(ctx).first()

        assertEquals(BooleanValue(false), result)
    }
}