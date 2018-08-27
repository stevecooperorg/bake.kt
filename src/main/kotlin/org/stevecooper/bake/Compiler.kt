package org.stevecooper.bake

import org.antlr.v4.runtime.ANTLRInputStream
import org.antlr.v4.runtime.CommonTokenStream
import org.stevecooper.bake.compiler.BakeLexer
import org.stevecooper.bake.compiler.BakeParser

class Compiler {

    fun toTokens(code: String): CommonTokenStream {
        val stream = ANTLRInputStream(code)
        val lexer = BakeLexer(stream)
        val tokens = CommonTokenStream(lexer)
        return tokens;
    }

    fun readLarder(code: String): BakeParser.LarderContext? {
        val tokens = toTokens(code)
        val parser = BakeParser(tokens)
        val result = parser.larder()
        return result
    }
}