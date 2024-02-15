package nl.mvdr.adventofcode.adventofcode2015.day07

import nl.mvdr.adventofcode.FunctionSolverTest
import org.junit.jupiter.params.provider.Arguments

class SomeAssemblyRequiredPart2Test: FunctionSolverTest<Int>(::solvePart2) {
    companion object {

        @JvmStatic
        fun testSolution(): List<Arguments> {
            return listOf(
                Arguments.of("14710", "input-day07-2015.txt")
            )
        }
    }
}