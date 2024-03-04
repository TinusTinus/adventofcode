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
    fun score(): Int = Property.entries
        .filter { it != Property.CALORIES }
        .map(::score)
        .reduce(Int::times)

    /**
     * Determines the score for the given [property].
     */
    fun score(property: Property) = max(0, ingredients.map { it.value * it.key.properties[property]!! }.sum())
}