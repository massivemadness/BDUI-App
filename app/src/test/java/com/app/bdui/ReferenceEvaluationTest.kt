package com.app.bdui

import com.app.bdui.core.domain.entity.StringValue
import com.app.bdui.core.domain.evaluation.EvalContext
import com.app.bdui.core.domain.evaluation.Reference
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Test

class ReferenceEvaluationTest {

    private val ctx = EvalContext(emptyMap())

    @Test
    fun `When reference is provided Then return value from context`() = runTest {
        val key = "reference_key"
        ctx.set(key, StringValue("reference_value"))

        val evaluation = Reference(key)

        val result = evaluation.eval(ctx).first()

        assertEquals(StringValue("reference_value"), result)
    }
}