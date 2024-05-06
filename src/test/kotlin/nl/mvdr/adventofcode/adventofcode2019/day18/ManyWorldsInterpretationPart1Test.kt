package nl.mvdr.adventofcode.adventofcode2019.day18;

import nl.mvdr.adventofcode.FunctionSolverTest
import nl.mvdr.adventofcode.SolverTest
import org.junit.jupiter.params.provider.Arguments

class LanternFishPart1Test: SolverTest<ManyWorldsInterpretationPart1>(ManyWorldsInterpretationPart1()) { // TODO FunctionSolverTest<Long>(::solvePart1) {
    companion object {
        @JvmStatic
        fun testSolution() = listOf(
            Arguments.of("8", "example-day18-2019-0.txt"),
            Arguments.of("6", "example-day18-2019-1.txt"),
            Arguments.of("0", "example-day18-2019-2.txt"),
            Arguments.of("86", "example-day18-2019-3.txt"),
            Arguments.of("132", "example-day18-2019-4.txt"),
            Arguments.of("136", "example-day18-2019-5.txt"),
            Arguments.of("81", "example-day18-2019-6.txt"),
            Arguments.of("?", "input-day18-2019.txt")
        )
    }
}
