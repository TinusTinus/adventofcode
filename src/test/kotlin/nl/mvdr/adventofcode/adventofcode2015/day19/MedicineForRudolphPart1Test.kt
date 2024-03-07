package nl.mvdr.adventofcode.adventofcode2015.day19

import nl.mvdr.adventofcode.FunctionSolverTest
import org.junit.jupiter.params.provider.Arguments

class MedicineForRudolphPart1Test: FunctionSolverTest<Int>(::solvePart1) {

    companion object {
        @JvmStatic
        fun testSolution(): List<Arguments> = listOf(
            Arguments.of("4", "example-day19-2015-0.txt"),
            Arguments.of("7", "example-day19-2015-1.txt"),
            Arguments.of("509", "input-day19-2015.txt")
        )
    }
}
