package nl.mvdr.adventofcode.adventofcode2015.day16

data class AuntSue(val number: Int, private val compounds: Map<Compound, Int>) {
    fun matchesCompounds(outdatedRetroencabulator: Boolean = false) = compounds.all { matches(it.key, it.value, outdatedRetroencabulator) }
}

private fun matches(compound: Compound, value: Int, outdatedRetroencabulator: Boolean) = when {
    outdatedRetroencabulator && (compound == Compound.CATS || compound == Compound.TREES) -> compound.requiredNumber < value
    outdatedRetroencabulator && (compound == Compound.POMERANIANS || compound == Compound.GOLDFISH) -> value < compound.requiredNumber
    else -> value == compound.requiredNumber
}

/**
 * Parses the given [text] as the string representation of an Aunt Sue.
 * For example:
 * "Sue 18: perfumes: 6, cars: 7, goldfish: 3"
 */
fun parseSue(text: String): AuntSue {
    val indexOfFirstColon = text.indexOf(':')
    val number = text.substring(4, indexOfFirstColon).toInt()
    val compounds = text.substring(indexOfFirstColon + 2).split(", ").associate(::parseCompoundEntry)
    return AuntSue(number, compounds)
}

/**
 * Parses the given [text] as a single compound entry.
 * For example: "perfumes: 6".
 */
private fun parseCompoundEntry(text: String): Pair<Compound, Int> {
    val (compound, amount) = text.split(": ")
    return Pair(parseCompound(compound), amount.toInt())
}