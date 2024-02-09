package nl.mvdr.adventofcode

import java.util.stream.Stream

/**
 * Implementation of LinesSolver, which simply calls the provided [solverFunction].
 * The function's result, of type [R], is then converted to a string using its toString method.
 */
class FunctionSolver<R>(private val solverFunction: (List<String>) -> R): LinesSolver<R> {
    override fun solve(lines: Stream<String>?): R = solverFunction.invoke(lines!!.toList())
}