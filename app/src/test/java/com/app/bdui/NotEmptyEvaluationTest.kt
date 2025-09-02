package com.app.bdui

import com.app.bdui.core.domain.value.BooleanValue
import com.app.bdui.core.domain.entity.EvalContext
import com.app.bdui.core.domain.evaluation.Literal
import com.app.bdui.core.domain.evaluation.NotEmpty
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Test

class NotEmptyEvaluationTest {

    private val ctx = EvalContext(emptyMap())

    @Test
    fun `When string is empty Then returns false`() = runTest {
        val evaluation = NotEmpty(Literal(""))

        val result = evaluation.eval(ctx).first()

        assertEquals(BooleanValue(false), result)
    }

    @Test
    fun `When string is not empty Then returns true`() = runTest {
        val evaluation = NotEmpty(Literal("abc"))

        val result = evaluation.eval(ctx).first()

        assertEquals(BooleanValue(true), result)
    }
}