package nl.mvdr.adventofcode.adventofcode2025.day05

import nl.mvdr.adventofcode.range.LongRange

data class IngredientDatabase(val freshIdRanges: List<LongRange>, val ids: List<Long>) {

    fun countFreshIngredients() = ids.count { id -> freshIdRanges.any { it.contains(id) } }

    fun countTotalFreshIds() = freshIdRanges.sumOf(LongRange::size)
}

fun parseInput(lines: List<String>): IngredientDatabase {
    val emptyLineIndex = lines.indexOf("")

    val freshIdRanges = LongRange.reduce(lines.take(emptyLineIndex).map(LongRange::parse))
    val ids = lines.drop(emptyLineIndex + 1).map(String::toLong)

    return IngredientDatabase(freshIdRanges, ids)
}
