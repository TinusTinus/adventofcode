package nl.mvdr.adventofcode.adventofcode2021.day22

import nl.mvdr.adventofcode.FunctionSolverTest
import org.junit.jupiter.params.provider.Arguments

class ReactorRebootPart1Test: FunctionSolverTest<Any>(::solvePart1) {
    companion object {
        @JvmStatic
        fun testSolution() = listOf(
            Arguments.of("27", "example-day22-2021-0.txt"),
            Arguments.of("46", "example-day22-2021-1.txt"),
            Arguments.of("38", "example-day22-2021-2.txt"),
            Arguments.of("39", "example-day22-2021-3.txt"),
            Arguments.of("590784", "example-day22-2021-4.txt"),
            Arguments.of("0", "example-day22-2021-5.txt"),
            Arguments.of("590784", "example-day22-2021-6.txt"),
            Arguments.of("474140", "example-day22-2021-7.txt")
        )
    }
}