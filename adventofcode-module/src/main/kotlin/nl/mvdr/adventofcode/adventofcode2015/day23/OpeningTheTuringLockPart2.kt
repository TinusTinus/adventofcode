package nl.mvdr.adventofcode.adventofcode2015.day23

import io.github.oshai.kotlinlogging.KotlinLogging
import nl.mvdr.adventofcode.solver.FunctionSolver

private val logger = KotlinLogging.logger{}

fun solvePart2(lines: Sequence<String>): Int {
    val program = Program(lines)
    val initialState = State(mapOf(Pair(Register.A, 1), Pair(Register.B, 0)))
    val endState = program.execute(initialState)
    return endState.registers[Register.B]!!
}

fun main() {
    val result = FunctionSolver(::solvePart2).solve("input-day23-2015.txt")
    logger.info { result }
}
