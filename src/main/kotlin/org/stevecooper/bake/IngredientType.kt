package org.stevecooper.bake

class IngredientType(val code: String, val name: String, val units: String) {
    fun getDiscrete(): Boolean { return this.units == "1" }
    fun getUnitString(): String { return if (getDiscrete()) "" else units }
}