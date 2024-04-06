package nl.mvdr.adventofcode.adventofcode2021.day12

data class Cave(val name: String) {
    val size: CaveSize = when {
        name.all(Char::isUpperCase) -> CaveSize.BIG
        name.all(Char::isLowerCase) -> CaveSize.SMALL
        else -> throw IllegalArgumentException("Unexpected cave name: $name")
    }

    override fun toString(): String = name
}
