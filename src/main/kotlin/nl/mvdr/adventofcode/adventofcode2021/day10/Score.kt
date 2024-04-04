package nl.mvdr.adventofcode.adventofcode2021.day10


/**
 * Determines the syntax error score of the given [line].
 * Returns 0 if the line does not contain a syntax error.
 */
fun syntaxErrorScore(line: String) = score(line, ChunkType::syntaxErrorScore, { _ -> 0 } )

fun completionScore(line: String) = score(line, { _ -> 0 }, ChunkType::completionScore )

/**
 * Scores the given [suffix] of a line.
 * The given [syntaxErrorScore] function determines how to score a syntax error, given the mismatching closing bracket's type.
 * The given [completionScore] function determines how to score an autocomplete character.
 * The preceding part of the line must not contain any syntax errors, and result in the given list of [openChunks].
 */
fun score(suffix: String, syntaxErrorScore: (ChunkType) -> Int, completionScore: (ChunkType) -> Int, openChunks: List<ChunkType> = emptyList()): Int = when {
    suffix.isEmpty() -> when {
        openChunks.isEmpty() -> 0
        else -> completionScore.invoke(openChunks.first()) + 5 * score("", syntaxErrorScore, completionScore, openChunks.drop(1))
    }
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