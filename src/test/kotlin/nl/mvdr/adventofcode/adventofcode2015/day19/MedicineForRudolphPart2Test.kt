package nl.mvdr.adventofcode.adventofcode2015.day19

import nl.mvdr.adventofcode.FunctionSolverTest
import org.junit.jupiter.params.provider.Arguments

class MedicineForRudolphPart2Test: FunctionSolverTest<Int>(::solvePart2) {

    companion object {
        @JvmStatic
        fun testSolution(): List<Arguments> = listOf(
            Arguments.of("3", "example-day19-2015-2.txt"),
            Arguments.of("6", "example-day19-2015-3.txt"),
            Arguments.of("?", "input-day19-2015.txt")
        )
    }
}
