package nl.mvdr.adventofcode.adventofcode2015.day16

enum class Compound(val text: String, val requiredNumber: Int) {
    CHILDREN("children", 3),
    CATS("cats", 7),
    SAMOYEDS("samoyeds", 2),
    POMERANIANS("pomeranians", 3),
    AKITAS("akitas", 0),
    VIZSLAS("vizslas", 0),
    GOLDFISH("goldfish", 5),
    TREES("trees", 3),
    CARS("cars", 2),
    PERFUMES("perfumes", 1)
}

fun parseCompound(text: String) = Compound.entries.find { it.text == text }!!
