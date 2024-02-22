package nl.mvdr.adventofcode.adventofcode2015.day10

import nl.mvdr.adventofcode.FunctionSolverTest
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.params.provider.Arguments

@Disabled // This takes a long time to run (but it does eventually terminate with the correct result!)
class ElvesLookElvesSayPart2Test: FunctionSolverTest<Int>(::solvePart2) {
    companion object {
        @JvmStatic
        fun testSolution(): List<Arguments> = listOf(
            Arguments.of("4666278", "input-day10-2015.txt")
        )
    }
}
