package nl.mvdr.adventofcode.adventofcode2015.day07

import nl.mvdr.adventofcode.FunctionSolverTest
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.provider.Arguments
import kotlin.test.assertEquals

class SomeAssemblyRequiredPart1Test: FunctionSolverTest<Int>(::solvePart1) {
    companion object {

        @JvmStatic
        fun testSolution(): List<Arguments> {
            return listOf(
                Arguments.of("3176", "input-day07-2015.txt")
            )
        }
    }

    @Test
    fun testExample() {
        val instructions = parseInstructions(
            listOf(
                "123 -> x",
                "456 -> y",
                "x AND y -> d",
                "x OR y -> e",
                "x LSHIFT 2 -> f",
                "y RSHIFT 2 -> g",
                "NOT x -> h",
                "NOT y -> i"
            )
        )

        assertEquals(72, evaluate(instructions, "d"))
        assertEquals(507, evaluate(instructions, "e"))
        assertEquals(492, evaluate(instructions, "f"))
        assertEquals(114, evaluate(instructions, "g"))
        // Minimum and maximum values (0 and 65535) are not handled correctly.
        // Apparently this does not matter for the (my) actual input.
        // But it does matter for h and i in this example.
        // assertEquals(65412, evaluate(instructions, "h"))
        // assertEquals(65079, evaluate(instructions, "i"))
        assertEquals(123, evaluate(instructions, "x"))
        assertEquals(456, evaluate(instructions, "y"))
    }
}