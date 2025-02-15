package nl.mvdr.adventofcode.adventofcode2021.day14

/**
 * A multiset of the elements in a polymer.
 * Note that this multiset can count up to very high numbers, due to element counts being kept using Long values.
 */
data class ElementMultiSet(private val counts: Map<Char, Long>) {

    infix fun union(other: ElementMultiSet): ElementMultiSet {
        val newCounts = counts.toMutableMap()
        other.counts.forEach { (element, count) -> newCounts[element] = (newCounts[element] ?: 0L) + count }
        return ElementMultiSet(newCounts)
    }

    fun mostCommonElementCount() = counts.values.max()

    fun leastCommonElementCount() = counts.values.min()
}

fun elementSetOf(element: Char) = ElementMultiSet(mapOf(Pair(element, 1L)))

fun elementSetOf(polymerTemplate: String): ElementMultiSet = polymerTemplate.map(::elementSetOf).reduce { set0, set1 -> set0 union set1 }

fun emptyElementSet() = ElementMultiSet(emptyMap())
