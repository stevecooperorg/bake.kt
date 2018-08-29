package org.stevecooper.bake

import org.stevecooper.bake.compiler.BakeBaseListener
import org.stevecooper.bake.compiler.BakeParser

class BakeListener : BakeBaseListener() {

    fun result(): ParseResult {
        return ParseResult(emptyList(), ingredientTypes.toList(), recipes.toList())
    }

    private fun extractString(text: String): String {
        return text.substring(1, text.length - 1)
    }

    private val ingredientTypes : MutableList<IngredientType> = mutableListOf()
    private val recipes : MutableList<Recipe> = mutableListOf()

    override fun exitRecipe(ctx: BakeParser.RecipeContext?) {
        super.exitRecipe(ctx)

        if (ctx == null) {
            return
        }

        var recipe = Recipe(ctx.name.text)
        this.recipes.add(recipe)
    }

    override fun exitAction(ctx: BakeParser.ActionContext?) {
        super.enterAction(ctx)

        if (ctx == null) {
            return
        }

        var ingredients = ctx.INGREDIENT_AMOUNT().map { it.text }
        println("ACTION:::" + ctx.act.text)
    }

    override fun exitPot(ctx: BakeParser.PotContext?) {
        super.enterPot(ctx)

        if (ctx == null) {
            return
        }

        println("POT:::" + ctx.potId.text)
    }

    override fun exitIngredientType(ctx: BakeParser.IngredientTypeContext?) {
        super.exitIngredientType(ctx)

        if (ctx == null) {
            return
        }

        var ingredientContext: BakeParser.IngredientTypeContext = ctx

        val ingredientType = IngredientType(
                ingredientContext.code.text,
                extractString(ingredientContext.name.text),
                extractString(ingredientContext.units.text))

        ingredientTypes.add(ingredientType)

    }

}