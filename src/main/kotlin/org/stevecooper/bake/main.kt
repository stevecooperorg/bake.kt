package org.stevecooper.bake

fun main(args: Array<String>) {
    println("Bake")
    val compiler = Compiler()
    val parseResult = compiler.readLarder("(BaPo - \"baking powder\", \"tsp\")", "/foo/bar/larder.txt")
    if (parseResult.fail()) {
        parseResult.syntaxErrors.forEach {println("${it.file}(${it.line}:${it.charPositionInLine}) ${it.msg}")}
    } else {
        parseResult.ingredientTypes.forEach { println("${it.name} is measured in ${it.getUnitString()} and uses the code ${it.code}")}
    }
}
