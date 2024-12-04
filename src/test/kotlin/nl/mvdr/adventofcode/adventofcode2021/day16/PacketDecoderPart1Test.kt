package nl.mvdr.adventofcode.adventofcode2021.day16

import nl.mvdr.adventofcode.FunctionSolverTest
import org.junit.jupiter.params.provider.Arguments

class PacketDecoderPart1Test: FunctionSolverTest<Int>(::solvePart1) {
    companion object {
        @JvmStatic
        fun testSolution() = listOf(
            Arguments.of("16", "example-day16-2021-0.txt"),
            Arguments.of("12", "example-day16-2021-1.txt"),
            Arguments.of("23", "example-day16-2021-2.txt"),
            Arguments.of("31", "example-day16-2021-3.txt")
        )
    }
}