package nl.mvdr.adventofcode.adventofcode2021.day22

import nl.mvdr.adventofcode.FunctionSolverTest
import org.junit.jupiter.params.provider.Arguments

class ReactorRebootPart2Test: FunctionSolverTest<Any>(::solvePart2) {
    companion object {
        @JvmStatic
        fun testSolution() = listOf(
            Arguments.of("27", "example-day22-2021-0.txt"),
            Arguments.of("46", "example-day22-2021-1.txt"),
            Arguments.of("38", "example-day22-2021-2.txt"),
            Arguments.of("39", "example-day22-2021-3.txt"),
            Arguments.of("590784", "example-day22-2021-4.txt"),
            Arguments.of("2758514936282235", "example-day22-2021-7.txt")
        )
    }
}