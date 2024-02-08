package nl.mvdr.adventofcode.adventofcode2015.day04

import nl.mvdr.adventofcode.SolverTest
import org.junit.jupiter.params.provider.Arguments

class IdealStockingStufferPart2Test: SolverTest<IdealStockingStufferPart2>(IdealStockingStufferPart2::class.java) {
    companion object {
        /** @return arguments for {@link SolverTest#testSolution(String, String)} */
        @JvmStatic
        fun testSolution(): List<Arguments> {
            return listOf(
                Arguments.of("1038736", "input-day04-2015.txt")
            )
        }
    }
}