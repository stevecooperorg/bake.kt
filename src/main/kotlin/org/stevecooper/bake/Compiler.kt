package org.stevecooper.bake

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
        return tokens;
    }

    fun extract(parser: BakeParser, result: ParseTree) : ParseResult {
        if (result == null) {
            Exception("could not parse")
        }

        val walker = ParseTreeWalker() // create standard walker
        val extractor = BakeListener(parser)
        walker.walk(extractor, result) // initiate walk of tree with listener

        return extractor.result();
    }

    fun readLarder(code: String): ParseResult {
        val tokens = toTokens(code)
        val parser = BakeParser(tokens)
        val result = parser.larder()
        return extract(parser, result)
    }
}