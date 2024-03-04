package nl.mvdr.adventofcode.adventofcode2015.day15

import io.github.oshai.kotlinlogging.KotlinLogging
import nl.mvdr.adventofcode.FunctionSolver

private val logger = KotlinLogging.logger{}

fun solvePart1(lines: List<String>): Int {
    val ingredients = lines.map(::parseIngredient)
    return getPossibleCookies(ingredients).maxOf(Cookie::score)
}

/**
 * Determines the possible cookies which can be made using the given [ingredients].
 */
private fun getPossibleCookies(ingredients: List<Ingredient>) = partition(ingredients).map(::Cookie)

/**
 * Partitions the given [ingredients].
 * Returns a map containing, per ingredient, the number of teaspoons to use when making a cookie.
 * The totals add up to the given [targetValue].
 */
private fun partition(ingredients: List<Ingredient>, targetValue: Int = 100): Set<Map<Ingredient, Int>> = when {
    ingredients.isEmpty() -> throw IllegalArgumentException("Ingredients are required!")
    ingredients.size == 1 -> setOf(mapOf(Pair(ingredients.first(), targetValue)))
    else -> {
        val ingredient = ingredients.first()
        val remainingIngredients = ingredients.drop(1)
        (0..targetValue).flatMap { teaspoons ->
            partition(remainingIngredients, targetValue - teaspoons)
                .map { it + mapOf(Pair(ingredient, teaspoons)) }
        }.toSet()
    }
}

fun main() {
    val result = FunctionSolver(::solvePart1).solve("input-day15-2015.txt")
    logger.info { result }
}
