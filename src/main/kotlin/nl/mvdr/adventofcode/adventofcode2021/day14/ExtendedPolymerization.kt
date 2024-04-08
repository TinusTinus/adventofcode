package nl.mvdr.adventofcode.adventofcode2021.day14

/**
 * Solves the puzzle, given the [lines] from the puzzle input and the number of pair insertion [steps] to perform.
 */
fun solve(lines: List<String>, steps: Int): Long {
    val polymerTemplate = lines[0]
    if (lines[1].isNotEmpty()) {
        throw IllegalArgumentException("Expected second line to be empty in the input, but was: " + lines[1])
    }
    val insertionRules = parseInsertionRules(lines.drop(2))

    val elements = elementSetOf(polymerTemplate) union countInsertedElements(polymerTemplate, insertionRules, steps)
    return elements.mostCommonElementCount() - elements.leastCommonElementCount()
}

private fun parseInsertionRules(lines: List<String>): Map<Pair<Char, Char>, Char> {
    val insertionRules = mutableMapOf<Pair<Char, Char>, Char>()
    for (insertionRuleString in lines) {
        val (pairString, insertionString) = insertionRuleString.split(" -> ")
        if (pairString.length != 2 || insertionString.length != 1) {
            throw IllegalArgumentException("Unable to parse insertion rule: $insertionRuleString")
        }
        insertionRules[Pair(pairString.first(), pairString.last())] = insertionString.first()
    }
    return insertionRules
}

/**
 * Returns a multiset of the elements which are inserted into the given [polymerTemplate],
 * by applying the given [insertionRules] the given number of [steps].
 */
private fun countInsertedElements(polymerTemplate: String, insertionRules: Map<Pair<Char, Char>, Char>, steps: Int): ElementMultiSet {
    val insertedElementsPerRule = countInsertedElementsPerRule(insertionRules, steps)

    var result = emptyElementSet()
    for (i in 0 until polymerTemplate.length - 1) {
        val pair = Pair(polymerTemplate[i], polymerTemplate[i + 1])
        val inserted = insertedElementsPerRule[pair] ?: emptyElementSet()
        result = result union inserted
    }
    return result
}

/**
 * Returns, per insertion rule, a multiset of the elements which are inserted for each occurrence of the pair,
 * if the number of given pair insertion [steps] are performed.
 */
private fun countInsertedElementsPerRule(insertionRules: Map<Pair<Char, Char>, Char>, steps: Int): Map<Pair<Char, Char>, ElementMultiSet> {
    var result = insertionRules.mapValues { elementSetOf(it.value) }

    for (step in 1 until steps) {
        result = insertionRules.mapValues { elementSetOf(it.value) union
                (result[Pair(it.key.first, it.value)] ?: emptyElementSet()) union
                (result[Pair(it.value, it.key.second)] ?: emptyElementSet()) }
    }

    return result
}
