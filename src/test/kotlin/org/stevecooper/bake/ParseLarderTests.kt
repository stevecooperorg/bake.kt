package org.stevecooper.bake

import org.antlr.v4.runtime.ANTLRInputStream
import org.stevecooper.bake.compiler.BakeLexer
import kotlin.test.assertEquals
import kotlin.test.assertSame
import org.junit.Test as test

class ParseLarderTests {

    @test fun `can parse a larder item`() {
        val compiler = Compiler();
        val result = compiler.readLarder("(BaPo - \"baking powder\", \"tsp\")", "larder.test.file")
        assertEquals(result.ingredientTypes.size , 1)

        val ingredient = result.ingredientTypes[0]
        assertEquals("BaPo", ingredient.code)
        assertEquals("baking powder", ingredient.name)
        assertEquals("tsp", ingredient.units)
    }

    fun tokens(lexer:BakeLexer) : List<String> {
        return lexer.allTokens.map { lexer.ruleNames[it.type - 1] }
    }


    fun lexerForResource(resourceName: String) = BakeLexer(ANTLRInputStream(this.javaClass.getResourceAsStream("/${resourceName}.txt")))

    fun lexerForCode(code: String) = BakeLexer(ANTLRInputStream(code))

}