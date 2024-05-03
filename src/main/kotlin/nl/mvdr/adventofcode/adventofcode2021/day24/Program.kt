package nl.mvdr.adventofcode.adventofcode2021.day24

data class Program(val instructions: List<Instruction>)

/**
 * Parses the [lines] from the puzzle input as a program.
 */
fun parseProgram(lines: Sequence<String>) = Program(lines.map(::parseInstruction).toList())
