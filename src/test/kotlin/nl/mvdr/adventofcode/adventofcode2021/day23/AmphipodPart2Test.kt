package nl.mvdr.adventofcode.adventofcode2021.day23

import nl.mvdr.adventofcode.FunctionSolverTest
import org.junit.jupiter.params.provider.Arguments

class AmphipodPart2Test: FunctionSolverTest<Any>(::solvePart2) {
    companion object {
        @JvmStatic
        fun testSolution() = listOf(
            Arguments.of("44169", "example-day23-2021.txt"),
            Arguments.of("?", "input-day23-2021.txt")
        )
    }
}