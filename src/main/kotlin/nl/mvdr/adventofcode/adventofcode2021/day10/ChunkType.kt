package nl.mvdr.adventofcode.adventofcode2021.day10

enum class ChunkType(val openBracket: Char, val closeBracket: Char, val syntaxErrorScore: Int, val completionScore: Int) {
    ROUND('(', ')', 3, 1),
    SQUARE('[', ']', 57, 2),
    CURLY('{', '}', 1197, 3),
    ANGLE('<', '>', 25137, 4)
}

fun getType(character: Char) = ChunkType.entries.first { it.openBracket == character || it.closeBracket == character }
