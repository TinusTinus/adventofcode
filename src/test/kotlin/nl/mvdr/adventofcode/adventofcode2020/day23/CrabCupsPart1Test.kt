package nl.mvdr.adventofcode.adventofcode2020.day23

import nl.mvdr.adventofcode.FunctionSolver
import nl.mvdr.adventofcode.FunctionSolverTest
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.CsvSource

class CrabCupsPart1Test: FunctionSolverTest<Any>(::solvePart1) {
    companion object {
        @JvmStatic
        fun testSolution() = listOf(
            Arguments.of("67384529", "example-day23-2020.txt")
        )
    }

    @ParameterizedTest
    @CsvSource( value = [
        "0, 25467389, example-day23-2020.txt",
        "1, 54673289, example-day23-2020.txt",
        "2, 32546789, example-day23-2020.txt",
        "3, 34672589, example-day23-2020.txt",
        "4, 32584679, example-day23-2020.txt",
        "5, 36792584, example-day23-2020.txt",
        "6, 93672584, example-day23-2020.txt",
        "7, 92583674, example-day23-2020.txt",
        "8, 58392674, example-day23-2020.txt",
        "9, 83926574, example-day23-2020.txt",
        "10, 92658374, example-day23-2020.txt"
    ] )
    fun test(turns: Int, expectedOutput: String, inputFile: String) {
        val solver = FunctionSolver<Any>{ input -> solvePart1(input, turns) }
        assertSolution(solver, expectedOutput, inputFile)
    }

}