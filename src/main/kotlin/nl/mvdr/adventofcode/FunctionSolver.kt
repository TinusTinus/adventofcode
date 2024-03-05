package nl.mvdr.adventofcode

import java.util.stream.Stream
import kotlin.streams.asSequence

/**
 * Implementation of LinesSolver, which simply calls the provided [solverFunction].
 * The function's result, of type [R], is then converted to a string using its toString method.
 */
class FunctionSolver<R>(private val solverFunction: (Sequence<String>) -> R): LinesSolver<R> {
    override fun solve(lines: Stream<String>?): R = solverFunction.invoke(lines!!.asSequence())
}