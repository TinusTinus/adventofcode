package nl.mvdr.adventofcode.adventofcode2021.day10

enum class ChunkType(val openBracket: Char, val closeBracket: Char, val syntaxErrorScore: Int) {
    ROUND('(', ')', 3),
    SQUARE('[', ']', 57),
    CURLY('{', '}', 1197),
    ANGLE('<', '>', 25137)
}

fun getType(character: Char) = ChunkType.entries.first { it.openBracket == character || it.closeBracket == character }
