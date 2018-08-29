package org.stevecooper.bake

import org.antlr.v4.runtime.ANTLRInputStream
import org.antlr.v4.runtime.CommonTokenStream
import org.antlr.v4.runtime.tree.ParseTreeWalker
import org.stevecooper.bake.compiler.BakeLexer
import org.stevecooper.bake.compiler.BakeParser
import javax.naming.PartialResultException

class Compiler {

    private var aggregateResult = ParseResult(emptyList(), emptyList(), emptyList())

    fun readCookbook(code: String, file: String) : ParseResult {
        // set up error listener, input stream, etc
        val errorListener = ListErrorListener(file)
        val stream = ANTLRInputStream(code)

        // the lexer needs to be listening for token recognition exceptions
        val lexer = BakeLexer(stream)
        lexer.removeErrorListeners()
        lexer.addErrorListener(errorListener)
        val tokens = CommonTokenStream(lexer)

        // the parser also listens for syntax errors
        val parser = BakeParser(tokens)
        parser.addErrorListener(errorListener)
        val result = parser.cookbook()

        if (result == null) {
            throw Exception("could not parse")
        }

        val walker = ParseTreeWalker() // create standard walker
        val extractor = BakeListener()
        walker.walk(extractor, result) // initiate walk of tree with listener

        val partialResult = extractor.result()
        this.aggregateResult = ParseResult(
                this.aggregateResult.syntaxErrors.plus(errorListener.toList()),
                this.aggregateResult.ingredientTypes,
                this.aggregateResult.recipes)
        this.aggregateResult = this.aggregateResult.merge(partialResult)
        return partialResult
    }

    fun result(): ParseResult {
        return this.aggregateResult
    }
}