package nl.mvdr.adventofcode.adventofcode2021.day13

import io.github.oshai.kotlinlogging.KotlinLogging
import nl.mvdr.adventofcode.FunctionSolver
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource

private val logger = KotlinLogging.logger{}

class TransparentOrigamiPart2Test {

    /**
     * Invokes the [solvePart2] function.
     * Note that this test does not contain any assertions; it only checks that the function completes without exceptions.
     * The resulting output (ASCII art) can be inspected visually in the console.
     */
    @ParameterizedTest
    @MethodSource
    fun test(inputFile: String) {
        val solution = FunctionSolver(::solvePart2).solve(inputFile)

        logger.info { solution }
    }

    companion object {
        @JvmStatic
        fun test(): List<Arguments> = listOf(
            Arguments.of("example-day13-2021.txt"), // expected result: a square
            Arguments.of("input-day13-2021.txt") // expected result: eight capital letters
        )
    }

}