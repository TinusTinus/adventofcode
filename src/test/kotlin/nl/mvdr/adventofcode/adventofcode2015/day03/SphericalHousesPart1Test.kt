package nl.mvdr.adventofcode.adventofcode2015.day03

import nl.mvdr.adventofcode.SolverTest
import org.junit.jupiter.params.provider.Arguments

class SphericalHousesPart1Test: SolverTest<SphericalHousesPart1>(SphericalHousesPart1::class.java) {
    companion object {
        /** @return arguments for {@link SolverTest#testSolution(String, String)} */
        @JvmStatic
        fun testSolution(): List<Arguments> {
            return listOf(
                Arguments.of("2", "example-day03-2015-0.txt"),
                Arguments.of("4", "example-day03-2015-1.txt"),
                Arguments.of("2", "example-day03-2015-2.txt"),
                Arguments.of("2592", "input-day03-2015.txt")
            )
        }
    }
}