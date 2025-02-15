package nl.mvdr.adventofcode.adventofcode2021.day10

data class Score(val syntaxErrorScore: Int, val completionScore: Long)

/**
 * Scores the given (suffix of a) [line].
 * The preceding part of the line must not contain any syntax errors, and result in the given list of [openChunks].
 */
fun score(line: String, openChunks: List<ChunkType> = emptyList()): Score = when {
    line.isEmpty() -> Score(0, completionScore(openChunks))
    else -> {
        val character = line.first()
        val chunkType = getType(character)
        when (character) {
            chunkType.openBracket -> score(line.drop(1), openChunks + chunkType)
            chunkType.closeBracket -> when(openChunks.last()) {
                chunkType -> score(line.drop(1), openChunks.dropLast(1))
                else -> Score(chunkType.syntaxErrorScore, 0)
            }
            else -> throw IllegalArgumentException("Unexpected invalid character found: $character")
        }
    }
}

/**
 * Calculates the completion score for an incomplete line.
 * The list of [unclosedChunks] contains all chunks which were opened but not closed, in order.
 */
private fun completionScore(unclosedChunks: List<ChunkType>): Long = when {
    unclosedChunks.isEmpty() -> 0L
    else -> unclosedChunks.first().completionScore + 5 * completionScore(unclosedChunks.drop(1))
}
