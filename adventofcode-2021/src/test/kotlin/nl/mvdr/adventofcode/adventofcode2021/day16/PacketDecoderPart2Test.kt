package nl.mvdr.adventofcode.adventofcode2021.day16

import nl.mvdr.adventofcode.solver.FunctionSolverTest
import org.junit.jupiter.params.provider.Arguments

class PacketDecoderPart2Test: FunctionSolverTest<Long>(::solvePart2) {
    companion object {
        @JvmStatic
        fun testSolution() = listOf(
            Arguments.of("3", "example-day16-2021-4.txt"),
            Arguments.of("54", "example-day16-2021-5.txt"),
            Arguments.of("7", "example-day16-2021-6.txt"),
            Arguments.of("9", "example-day16-2021-7.txt"),
            Arguments.of("1", "example-day16-2021-8.txt"),
            Arguments.of("0", "example-day16-2021-9.txt"),
            Arguments.of("0", "example-day16-2021-10.txt"),
            Arguments.of("1", "example-day16-2021-11.txt")
        )
    }
}