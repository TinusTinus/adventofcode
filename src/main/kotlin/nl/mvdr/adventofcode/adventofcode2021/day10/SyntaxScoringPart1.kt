package nl.mvdr.adventofcode.adventofcode2021.day10

import io.github.oshai.kotlinlogging.KotlinLogging
import nl.mvdr.adventofcode.FunctionSolver

private val logger = KotlinLogging.logger{}

fun solvePart1(lines: Sequence<String>) = lines.sumOf(::score)

private fun score(line: String, openChunks: List<ChunkType> = emptyList()): Int = when {
    line.isEmpty() -> 0
    else -> {
        val character = line.first()
        val chunkType = getType(character)
        when (character) {
            chunkType.openBracket -> score(line.drop(1), openChunks + chunkType)
            chunkType.closeBracket -> when(openChunks.last()) {
                chunkType -> score(line.drop(1), openChunks.dropLast(1))
                else -> chunkType.syntaxErrorScore
            }
            else -> throw IllegalArgumentException("Unexpected invalid character found: $character")
        }
    }
}

fun main() {
    val result = FunctionSolver(::solvePart1).solve("input-day09-2021.txt")
    logger.info { result }
}
