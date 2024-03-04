package nl.mvdr.adventofcode.adventofcode2015.day15

import kotlin.math.max

data class Cookie(val ingredients: Map<Ingredient, Int>) {
    init {
        if (ingredients.values.sum() != 100) {
            throw IllegalArgumentException("Invalid ingredients: $ingredients. A cookie requires exactly 100 teaspoons of ingredients.")
        }
    }

    /**
     * Determines the score for this cookie.
     */
    fun score(): Int = ingredients.keys
        .first()
        .properties
        .keys
        .filter { it != "calories" }
        .map(::score)
        .reduce(Int::times)

    /**
     * Determines the score for the property with the given [propertyName].
     * For example: "capacity" or "durability".
     */
    fun score(propertyName: String) = max(0, ingredients.map { it.value * it.key.properties[propertyName]!! }.sum())
}