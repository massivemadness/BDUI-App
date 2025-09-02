package com.app.bdui

import com.app.bdui.core.domain.value.IntegerValue
import com.app.bdui.core.domain.entity.EvalContext
import com.app.bdui.core.domain.evaluation.Length
import com.app.bdui.core.domain.evaluation.Literal
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Test

class LengthEvaluationTest {

    private val ctx = EvalContext(emptyMap())

    @Test
    fun `When string is empty Then returns 0`() = runTest {
        val evaluation = Length(Literal(""))

        val result = evaluation.eval(ctx).first()

        assertEquals(IntegerValue(0), result)
    }

    @Test
    fun `When string is not empty Then returns string length`() = runTest {
        val evaluation = Length(Literal("abc"))

        val result = evaluation.eval(ctx).first()

        assertEquals(IntegerValue(3), result)
    }
}