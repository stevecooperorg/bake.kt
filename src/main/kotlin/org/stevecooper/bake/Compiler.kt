package org.stevecooper.bake

import org.antlr.v4.runtime.ANTLRInputStream
import org.antlr.v4.runtime.CommonTokenStream
import org.stevecooper.bake.compiler.HelloLexer
import org.stevecooper.bake.compiler.HelloParser

class Compiler {

    fun compileString(code: String) {
        val stream = ANTLRInputStream(code)
        val lexer = HelloLexer(stream)
        val tokens = CommonTokenStream(lexer)
        val parser = HelloParser(tokens)
        val result = parser.r()
    }
}