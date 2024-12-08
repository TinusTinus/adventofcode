package nl.mvdr.adventofcode.adventofcode2019.day18


import io.github.oshai.kotlinlogging.KotlinLogging
import nl.mvdr.adventofcode.solver.FunctionSolver
import nl.mvdr.adventofcode.point.Point

private val logger = KotlinLogging.logger{}

fun solvePart2(linesSequence: Sequence<String>): Int {
    val lines = linesSequence.toMutableList()

    val point = (0 until lines.size).flatMap { y -> (0 until lines[y].length).map { x -> Point(x, y) } }
        .first { lines[it.y][it.x] == '@' }

    lines[point.y - 1] = lines[point.y - 1].replaceRange(point.x - 1 .. point.x + 1, "@#@")
    lines[point.y] = lines[point.y].replaceRange(point.x - 1 .. point.x + 1, "###")
    lines[point.y + 1] = lines[point.y + 1].replaceRange(point.x - 1 .. point.x + 1, "@#@")

    return parseInput(lines).solve()
}

fun main() {
    val result = FunctionSolver(::solvePart2).solve("input-day18-2019.txt")
    logger.info { result }
}
