package org.stevecooper.bake

import org.antlr.v4.runtime.BaseErrorListener
import org.antlr.v4.runtime.RecognitionException
import org.antlr.v4.runtime.Recognizer

class ListErrorListener(private val file: String): BaseErrorListener() {

    private val errors: MutableList<SyntaxError> = mutableListOf()

    override fun syntaxError(recognizer: Recognizer<*, *>?, offendingSymbol: Any?, line: Int, charPositionInLine: Int, msg: String?, e: RecognitionException?) {
        super.syntaxError(recognizer, offendingSymbol, line, charPositionInLine, msg, e)

        val safeMsg = msg ?: ""

        errors.add(SyntaxError(file, line, charPositionInLine, safeMsg))


    }

    fun toList() : List<SyntaxError> {
        return errors.toList()
    }
}