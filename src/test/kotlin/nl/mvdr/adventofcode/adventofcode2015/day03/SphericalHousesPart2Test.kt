package nl.mvdr.adventofcode.adventofcode2015.day03

import nl.mvdr.adventofcode.SolverTest
import org.junit.jupiter.params.provider.Arguments

class SphericalHousesPart2Test: SolverTest<SphericalHousesPart2>(SphericalHousesPart2::class.java) {
    companion object {
        /** @return arguments for {@link SolverTest#testSolution(String, String)} */
        @JvmStatic
        fun testSolution(): List<Arguments> {
            return listOf(
                Arguments.of("3", "example-day03-2015-3.txt"),
                Arguments.of("3", "example-day03-2015-1.txt"),
                Arguments.of("11", "example-day03-2015-2.txt"),
                Arguments.of("2360", "input-day03-2015.txt")
            )
        }
    }
}