package org.stevecooper.bake

import org.antlr.v4.runtime.ANTLRInputStream
import org.antlr.v4.runtime.CommonTokenStream
import org.stevecooper.bake.compiler.HelloLexer
import org.stevecooper.bake.compiler.HelloParser

class Compiler {

    fun toTokens(code: String): CommonTokenStream {
        val stream = ANTLRInputStream(code)
        val lexer = HelloLexer(stream)
        val tokens = CommonTokenStream(lexer)
        return tokens;
    }

    fun compileString(code: String) {
        val tokens = toTokens(code)
        val parser = HelloParser(tokens)
        val result = parser.r()
    }

    fun readLarder(code: String) {
        throw NotImplementedError()
    }
}