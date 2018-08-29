package org.stevecooper.bake

import org.antlr.v4.runtime.ANTLRInputStream
import org.stevecooper.bake.compiler.BakeLexer
import org.stevecooper.util.readToString
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertSame
import org.junit.Test as test

class ParseLarderTests {

    @test fun `can parse a larder item`() {
        val compiler = Compiler()
        val result = compiler.readCookbook("INGREDIENT BaPo \"baking powder\" \"tsp\"", "larder.test.file")
        assertEquals(result.ingredientTypes.size , 1)

        val ingredient = result.ingredientTypes[0]
        assertFalse(result.fail())
        assertEquals("BaPo", ingredient.code)
        assertEquals("baking powder", ingredient.name)
        assertEquals("tsp", ingredient.units)
    }

    @test fun `can parse an entire larder`() {
        val compiler = Compiler()
        val file = "/Larder.txt"
        var larderContent = this.javaClass.getResourceAsStream(file).readToString()
        val result = compiler.readCookbook(larderContent, file)
        assertFalse(result.fail())
        assertEquals(33, result.ingredientTypes.size )
    }
}