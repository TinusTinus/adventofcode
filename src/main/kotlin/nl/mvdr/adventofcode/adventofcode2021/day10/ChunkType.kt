package nl.mvdr.adventofcode.adventofcode2021.day10

enum class ChunkType(val openBracket: Char, val closeBracket: Char, val syntaxErrorScore: Int, val completionScore: Long) {
    ROUND('(', ')', 3, 1L),
    SQUARE('[', ']', 57, 2L),
    CURLY('{', '}', 1197, 3L),
    ANGLE('<', '>', 25137, 4L)
}

fun getType(character: Char) = ChunkType.entries.first { it.openBracket == character || it.closeBracket == character }
