package nl.mvdr.adventofcode.adventofcode2015.day16

enum class Compound(val text: String) {
    CHILDREN("children"),
    CATS("cats"),
    SAMOYEDS("samoyeds"),
    POMERANIANS("pomeranians"),
    AKITAS("akitas"),
    VIZSLAS("vizslas"),
    GOLDFISH("goldfish"),
    TREES("trees"),
    CARS("cars"),
    PERFUMES("perfumes")
}

fun parseCompound(text: String) = Compound.entries.find { it.text == text }!!
