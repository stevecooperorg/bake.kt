package org.stevecooper.bake

fun main(args: Array<String>) {
    println("Bake")
    val compiler = Compiler()
    compiler.readCookbook("""
INGREDIENT Butt "softened unsalted butter" "g"
INGREDIENT LbSu "light brown sugar" "g"
INGREDIENT GoSy "golden syrup" "g"
INGREDIENT DblC "double cream" "ml"
INGREDIENT Salt "table salt" "g"

RECIPE caramel-sauce .pan 75Butt 100LbSu 50GoSy heat stir remove-from-heat 125DblC 3Salt stir-in 3Salt
""", "literal")
    val parseResult = compiler.result()

    if (parseResult.fail()) {
        parseResult.syntaxErrors.forEach { println("${it.file}(${it.line}:${it.charPositionInLine}) ${it.msg}") }
    } else {
        println("Parse Successful")
        parseResult.ingredientTypes.forEach { println("Ingredient: ${it.name} is measured in ${it.getUnitString()} and uses the code ${it.code}") }
        parseResult.recipes.forEach { println("Recipe: ${it.name}") }
    }
}
