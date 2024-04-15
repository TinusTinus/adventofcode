package nl.mvdr.adventofcode.adventofcode2021.day19

import nl.mvdr.adventofcode.FunctionSolverTest
import org.junit.jupiter.params.provider.Arguments

class BeaconScannerPart1Test: FunctionSolverTest<Any>(::solvePart1) {
    companion object {
        @JvmStatic
        fun testSolution() = listOf(
            Arguments.of("79", "example-day19-2021.txt"),
            Arguments.of("?", "input-day19-2021.txt")
        )
    }
}