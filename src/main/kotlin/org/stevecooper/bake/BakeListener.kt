package org.stevecooper.bake

import org.stevecooper.bake.compiler.BakeBaseListener
import org.stevecooper.bake.compiler.BakeParser

class BakeListener(private val parser: BakeParser): BakeBaseListener() {

    fun result(): ParseResult {
        return ParseResult(ingredientTypes.toList())
    }

    private fun extractString(text: String): String {
        return text.substring(1, text.length - 1)
    }

    private val ingredientTypes : MutableList<IngredientType> = mutableListOf<IngredientType>()

    override fun exitIngredientType(ctx: BakeParser.IngredientTypeContext?) {
        super.exitIngredientType(ctx)

        if (ctx == null) {
            return
        }

        var ingredientContext: BakeParser.IngredientTypeContext = ctx;

        val ingredientType = IngredientType(
                ingredientContext.code.text,
                extractString(ingredientContext.name.text),
                extractString(ingredientContext.units.text))

        ingredientTypes.add(ingredientType)

    }

}