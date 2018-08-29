package org.stevecooper.bake

class ParseResult(val syntaxErrors: List<SyntaxError>,val ingredientTypes: List<IngredientType>) {
    fun fail() : Boolean {
        return syntaxErrors.any()
    }
}