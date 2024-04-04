package nl.mvdr.adventofcode.adventofcode2021.day10


/**
 * Determines the syntax error score of the given [line].
 * Returns 0 if the line does not contain a syntax error.
 */
fun syntaxErrorScore(line: String): Int = score(line, ChunkType::syntaxErrorScore)

/**
 * Scores the given [suffix] of a line.
 * The preceding part of the line must not contain any syntax errors, and result in the given list of [openChunks].
 */
fun score(suffix: String, syntaxErrorScore: (ChunkType) -> Int, openChunks: List<ChunkType> = emptyList(),): Int = when {
    suffix.isEmpty() -> 0
    else -> {
        val character = suffix.first()
        val chunkType = getType(character)
        when (character) {
            chunkType.openBracket -> score(suffix.drop(1), syntaxErrorScore, openChunks + chunkType)
            chunkType.closeBracket -> when(openChunks.last()) {
                chunkType -> score(suffix.drop(1), syntaxErrorScore, openChunks.dropLast(1))
                else -> syntaxErrorScore.invoke(chunkType)
            }
            else -> throw IllegalArgumentException("Unexpected invalid character found: $character")
        }
    }
}