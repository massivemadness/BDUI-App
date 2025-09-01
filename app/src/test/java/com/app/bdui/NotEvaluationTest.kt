package com.app.bdui

import com.app.bdui.core.domain.entity.BooleanValue
import com.app.bdui.core.domain.evaluation.EvalContext
import com.app.bdui.core.domain.evaluation.Literal
import com.app.bdui.core.domain.evaluation.Not
import com.app.bdui.core.domain.evaluation.NotEmpty
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Test

class NotEvaluationTest {

    private val ctx = EvalContext(emptyMap())

    @Test
    fun `When target is false Then returns true`() = runTest {
        val evaluation = Not(Literal(false))

        val result = evaluation.eval(ctx).first()

        assertEquals(BooleanValue(true), result)
    }

    @Test
    fun `When target is true Then returns false`() = runTest {
        val evaluation = Not(Literal(true))

        val result = evaluation.eval(ctx).first()

        assertEquals(BooleanValue(false), result)
    }
}