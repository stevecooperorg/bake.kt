package org.stevecooper.bake

class ParseResult(val syntaxErrors: List<SyntaxError>,val ingredientTypes: List<IngredientType>, val recipes: List<Recipe>) {
    fun fail() : Boolean {
        return syntaxErrors.any()
    }

    fun merge(otherResult: ParseResult):ParseResult {
        return ParseResult(
                this.syntaxErrors.plus(otherResult.syntaxErrors),
                this.ingredientTypes.plus(otherResult.ingredientTypes),
                this.recipes.plus(otherResult.recipes))
    }
}