package nl.mvdr.adventofcode.adventofcode2021.day12

data class Cave(val name: String) {
    val big: Boolean = when {
        name.all(Char::isUpperCase) -> true
        name.all(Char::isLowerCase) -> false
        else -> throw IllegalArgumentException("Unexpected cave name: $name")
    }

    override fun toString(): String = name
}
