package nl.mvdr.adventofcode.adventofcode2021.day14

data class Manual(private val polymerTemplate: String, private val insertionRules: Map<Pair<Char, Char>, Char>) {

    /**
     * Solves the puzzle for the given number of pair insertion [steps].
     */
    fun solve(steps: Int): Long {
        val elements = elementSetOf(polymerTemplate) union countInsertedElements(steps)
        return elements.mostCommonElementCount() - elements.leastCommonElementCount()
    }

    /**
     * Returns a multiset of the elements which are inserted into the polymer template,
     * by applying the insertion rules the given number of [steps].
     */
    private fun countInsertedElements(steps: Int): ElementMultiSet {
        val insertedElementsPerRule = countInsertedElementsPerRule(steps)

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
    private fun countInsertedElementsPerRule(steps: Int): Map<Pair<Char, Char>, ElementMultiSet> {
        var result = insertionRules.mapValues { elementSetOf(it.value) }

        for (step in 1 until steps) {
            result = insertionRules.mapValues { elementSetOf(it.value) union
                    (result[Pair(it.key.first, it.value)] ?: emptyElementSet()) union
                    (result[Pair(it.value, it.key.second)] ?: emptyElementSet()) }
        }

        return result
    }
}

fun parseManual(lines: List<String>) = Manual(lines[0], parseInsertionRules(lines.drop(2)))

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
