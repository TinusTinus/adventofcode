package nl.mvdr.adventofcode.adventofcode2015.day15

import kotlin.math.max

/**
 * Number of teaspoons of ingredients per cookie.
 */
private const val TEASPOONS_PER_COOKIE = 100

data class Cookie(val ingredients: Map<Ingredient, Int>) {
    init {
        require(ingredients.values.sum() == TEASPOONS_PER_COOKIE) { "Exactly $TEASPOONS_PER_COOKIE teaspoons of ingredients are required." }
    }

    val calories: Int get() = ingredients.map { it.key.calories * it.value }.sum()

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

/**
 * Determines the possible cookies which can be made using the ingredients as specified in the given [lines].
 */
fun getPossibleCookies(lines: Sequence<String>) = partition(lines.map(::parseIngredient).toList()).map(::Cookie)

/**
 * Partitions the given [ingredients].
 * Returns a map containing, per ingredient, the number of teaspoons to use when making a cookie.
 * The totals add up to the given [targetValue].
 */
private fun partition(ingredients: List<Ingredient>, targetValue: Int = TEASPOONS_PER_COOKIE): List<Map<Ingredient, Int>> = when {
    ingredients.isEmpty() -> throw IllegalArgumentException("Ingredients are required!")
    ingredients.size == 1 -> listOf(mapOf(Pair(ingredients.first(), targetValue)))
    else -> {
        val ingredient = ingredients.first()
        val remainingIngredients = ingredients.drop(1)
        (0..targetValue).flatMap { teaspoons ->
            partition(remainingIngredients, targetValue - teaspoons)
                .map { it + mapOf(Pair(ingredient, teaspoons)) }
        }
    }
}
