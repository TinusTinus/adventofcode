package nl.mvdr.adventofcode.adventofcode2015.day07

import nl.mvdr.adventofcode.FunctionSolverTest
import org.junit.jupiter.params.provider.Arguments

class SomeAssemblyRequiredPart1Test: FunctionSolverTest<Int>(::solvePart1) {
    companion object {

        @JvmStatic
        fun testSolution(): List<Arguments> {
            return listOf(
                Arguments.of("3176", "input-day07-2015.txt")
            )
        }
    }
}