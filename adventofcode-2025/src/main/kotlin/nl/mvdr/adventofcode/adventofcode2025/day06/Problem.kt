package nl.mvdr.adventofcode.adventofcode2025.day06

data class Problem(val numbers: List<Long>, val operator: String) {
    fun solve() = when(operator) {
        "+" -> numbers.sum()
        "*" -> numbers.reduce(Long::times)
        else -> throw IllegalStateException("Unexpected operator: $operator")
    }
}

fun parse(lines: List<String>): List<Problem> {
    val spaces = Regex(" +")

    val operators = lines.last()
        .trim()
        .split(spaces)

    val numberLists = lines.dropLast(1)
        .map(String::trim)
        .map { it.split(spaces).map(String::toLong) }

    return (0 ..< operators.size).map { i -> Problem(numberLists.map { it[i] }, operators[i] ) }
}
