package org.stevecooper.bake

import org.antlr.v4.runtime.ANTLRInputStream
import org.antlr.v4.runtime.CommonTokenStream
import org.antlr.v4.runtime.ParserRuleContext
import org.antlr.v4.runtime.RuleContext
import org.stevecooper.bake.compiler.HelloLexer
import org.stevecooper.bake.compiler.HelloParser
import java.io.StringReader

fun main(args: Array<String>) {
    println("Hello")
    val validInput = "hello world"
    compileString(validInput)
}

fun compileString(code: String) {
    val stream = ANTLRInputStream(code)
    val lexer = HelloLexer(stream)
    val tokens = CommonTokenStream(lexer)
    val parser = HelloParser(tokens)
    val result = parser.r()
    //val astBuilder = ASTBuilder(moduleName, code)
    //if (explore) explore(result, 0)
    //return astBuilder.visitProgram(result)
}