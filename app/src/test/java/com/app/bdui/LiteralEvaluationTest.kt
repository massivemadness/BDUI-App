package com.app.bdui

import com.app.bdui.core.domain.entity.BooleanValue
import com.app.bdui.core.domain.entity.IntegerValue
import com.app.bdui.core.domain.entity.StringValue
import com.app.bdui.core.domain.evaluation.EvalContext
import com.app.bdui.core.domain.evaluation.Literal
import com.app.bdui.core.domain.evaluation.Not
import com.app.bdui.core.domain.evaluation.NotEmpty
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Test

class LiteralEvaluationTest {

    private val ctx = EvalContext(emptyMap())

    @Test
    fun `When value is string Then return string`() = runTest {
        val evaluation = Literal("hello")

        val result = evaluation.eval(ctx).first()

        assertEquals(StringValue("hello"), result)
    }

    @Test
    fun `When value is boolean Then return boolean`() = runTest {
        val evaluation = Literal(false)

        val result = evaluation.eval(ctx).first()

        assertEquals(BooleanValue(false), result)
    }

    @Test
    fun `When value is integer Then return integer`() = runTest {
        val evaluation = Literal(123)

        val result = evaluation.eval(ctx).first()

        assertEquals(IntegerValue(123), result)
    }
}