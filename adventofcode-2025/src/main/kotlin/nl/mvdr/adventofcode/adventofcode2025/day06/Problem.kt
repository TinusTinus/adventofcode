package nl.mvdr.adventofcode.adventofcode2025.day06

data class Problem(val numbers: List<Long>, val operator: String) {
    fun solve() = when(operator) {
        "+" -> numbers.sum()
        "*" -> numbers.reduce(Long::times)
        else -> throw IllegalStateException("Unexpected operator: $operator")
    }
}

fun parsePart1(lines: List<String>): List<Problem> {
    val spaces = Regex(" +")

    val operators = lines.last()
        .trim()
        .split(spaces)

    val numberLists = lines.dropLast(1)
        .map(String::trim)
        .map { it.split(spaces).map(String::toLong) }

    return (0 ..< operators.size).map { i -> Problem(numberLists.map { it[i] }, operators[i] ) }
}

fun parsePart2(lines: List<String>): List<Problem> {
    val columns = (0 ..< lines.first().length)
        .map { i -> lines.map { it[i].toString() }.reduce(String::plus) }

    val problems = mutableListOf<Problem>()
    var numbers = mutableListOf<Long>()
    var operator: String? = null
    for (column in columns) {
        if (column.all { it == ' ' }) {
            problems.add(Problem(numbers, operator!!))
            operator = null
            numbers = mutableListOf()
        } else {
            if (column.last() != ' ') {
                operator = column.last().toString()
            }
            numbers.add(column.dropLast(1).trim().toLong())
        }
    }
    problems.add(Problem(numbers, operator!!))

    return problems
}