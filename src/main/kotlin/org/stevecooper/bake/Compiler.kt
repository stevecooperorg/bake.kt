package org.stevecooper.bake

import org.antlr.v4.runtime.ANTLRErrorListener
import org.antlr.v4.runtime.ANTLRInputStream
import org.antlr.v4.runtime.CommonTokenStream
import org.antlr.v4.runtime.tree.ParseTree
import org.antlr.v4.runtime.tree.ParseTreeWalker
import org.stevecooper.bake.compiler.BakeLexer
import org.stevecooper.bake.compiler.BakeParser

class Compiler {

    fun toTokens(code: String): CommonTokenStream {
        val stream = ANTLRInputStream(code)
        val lexer = BakeLexer(stream)
        val tokens = CommonTokenStream(lexer)
        return tokens
    }

    fun extract(parser: BakeParser, result: ParseTree, errorListener: ListErrorListener) : ParseResult {
        if (result == null) {
            Exception("could not parse")
        }

        val syntaxErrorCount = parser.numberOfSyntaxErrors;
        if (syntaxErrorCount > 0) {
            val errors = errorListener.toList()
            return ParseResult(errors, emptyList())
        }

        val walker = ParseTreeWalker() // create standard walker
        val extractor = BakeListener(parser)
        walker.walk(extractor, result) // initiate walk of tree with listener

        return extractor.result()
    }

    fun readLarder(code: String, file: String): ParseResult {
        return parse(code, file) { it.larder() }
    }

    private fun parse(code: String, file: String, parse: (BakeParser) -> ParseTree): ParseResult {
        val tokens = toTokens(code)
        val parser = BakeParser(tokens)
        val errorListener = ListErrorListener(file)
        parser.addErrorListener(errorListener)
        val result = parse(parser)
        return extract(parser, result, errorListener)
    }
}