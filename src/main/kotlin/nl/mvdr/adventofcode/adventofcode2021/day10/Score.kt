package nl.mvdr.adventofcode.adventofcode2021.day10


/**
 * Determines the syntax error score of the given [line].
 * Returns 0 if the line does not contain a syntax error.
 */
fun syntaxErrorScore(line: String) = score(line, ChunkType::syntaxErrorScore, { _ -> 0 } )

fun containsSyntaxError(line: String) = syntaxErrorScore(line) != 0

fun completionScore(line: String): Int = score(line, { _ -> 0 }, ::completionScore )

private fun completionScore(unclosedChunks: List<ChunkType>): Int = when {
    unclosedChunks.isEmpty() -> 0
    else -> unclosedChunks.first().completionScore + 5 * completionScore(unclosedChunks.drop(1))
}

/**
 * Scores the given [suffix] of a line.
 * The given [syntaxErrorScore] function determines how to score a syntax error, given the mismatching closing bracket's type.
 * The given [completionScore] function determines how to score incomplete line, given the unclosed chunks.
 * The preceding part of the line must not contain any syntax errors, and result in the given list of [openChunks].
 */
private fun score(suffix: String, syntaxErrorScore: (ChunkType) -> Int, completionScore: (List<ChunkType>) -> Int, openChunks: List<ChunkType> = emptyList()): Int = when {
    suffix.isEmpty() -> completionScore.invoke(openChunks)
    else -> {
        val character = suffix.first()
        val chunkType = getType(character)
        when (character) {
            chunkType.openBracket -> score(suffix.drop(1), syntaxErrorScore, completionScore, openChunks + chunkType)
            chunkType.closeBracket -> when(openChunks.last()) {
                chunkType -> score(suffix.drop(1), syntaxErrorScore, completionScore, openChunks.dropLast(1))
                else -> syntaxErrorScore.invoke(chunkType)
            }
            else -> throw IllegalArgumentException("Unexpected invalid character found: $character")
        }
    }
}