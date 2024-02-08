package nl.mvdr.adventofcode.adventofcode2015.day04

import nl.mvdr.adventofcode.SolverTest
import org.junit.jupiter.params.provider.Arguments

class IdealStockingStufferPart1Test: SolverTest<IdealStockingStufferPart1>(IdealStockingStufferPart1::class.java) {
    companion object {
        /** @return arguments for {@link SolverTest#testSolution(String, String)} */
        @JvmStatic
        fun testSolution(): List<Arguments> {
            return listOf(
                Arguments.of("609043", "example-day04-2015-0.txt"),
                Arguments.of("1048970", "example-day04-2015-1.txt"),
                Arguments.of("254575", "input-day04-2015.txt")
            )
        }
    }
}