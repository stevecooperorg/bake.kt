package org.stevecooper.util

import kotlin.test.assertEquals
import kotlin.test.assertNotNull
import org.junit.Test as test

class stringUtilsTest {
    @test fun `can convert inputStream to string`() {
        var stream = this.javaClass.getResourceAsStream("/stringUtilsTest.content.txt")
        if (stream != null) {
            val content = stream.readToString()
            assertEquals("Here is my input data", content)
        }
    }
}