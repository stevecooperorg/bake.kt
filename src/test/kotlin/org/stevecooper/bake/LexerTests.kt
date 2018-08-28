package org.stevecooper.bake

import org.antlr.v4.runtime.ANTLRInputStream
import org.stevecooper.bake.compiler.BakeLexer
import kotlin.test.assertEquals
import org.junit.Test as test

class LexerTests {

    @test fun `can lex larder tokens`() {
        val actual = tokens(lexerForCode("( SrFl \"Bake\" , \"Str\" - -)"))
        val expected = listOf("OP", "INGREDIENT_CODE", "STRING", "COMMA", "STRING", "DASH", "DASH", "CL")
        assertEquals(expected, actual)
    }

    fun tokens(lexer:BakeLexer) : List<String> {
        return lexer.allTokens.map { lexer.ruleNames[it.type - 1] }
    }

    fun lexerForResource(resourceName: String) = BakeLexer(ANTLRInputStream(this.javaClass.getResourceAsStream("/${resourceName}.txt")))

    fun lexerForCode(code: String) = BakeLexer(ANTLRInputStream(code))

}