package nl.mvdr.adventofcode

import java.util.stream.Stream

/**
 * Implementation of {@link LinesSolver}, which simply calls the provided function.
 *
 * @param <R> result type
 * @param solverFunction solution for the puzzle
 * @author Martijn van de Rijdt
 */
class FunctionSolver<R>(private val solverFunction: (List<String>) -> R): LinesSolver<R> {
    override fun solve(lines: Stream<String>?): R = solverFunction.invoke(lines!!.toList())
}