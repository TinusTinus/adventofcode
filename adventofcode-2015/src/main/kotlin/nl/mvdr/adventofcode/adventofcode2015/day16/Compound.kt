package nl.mvdr.adventofcode.adventofcode2015.day16

enum class Compound(val text: String, private val requiredValue: Int) {
    CHILDREN("children", 3),
    CATS("cats", 7),
    SAMOYEDS("samoyeds", 2),
    POMERANIANS("pomeranians", 3),
    AKITAS("akitas", 0),
    VIZSLAS("vizslas", 0),
    GOLDFISH("goldfish", 5),
    TREES("trees", 3),
    CARS("cars", 2),
    PERFUMES("perfumes", 1);

    fun matches(value: Int, outdatedRetroencabulator: Boolean) = when {
        outdatedRetroencabulator && (this == CATS || this == TREES) -> requiredValue < value
        outdatedRetroencabulator && (this == POMERANIANS || this == GOLDFISH) -> value < requiredValue
        else -> value == requiredValue
    }
}

fun parseCompound(text: String) = Compound.entries.find { it.text == text }!!
