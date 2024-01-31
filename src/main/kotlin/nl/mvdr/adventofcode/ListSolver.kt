package nl.mvdr.adventofcode

import java.util.stream.Stream

/**
 * Convenience interface, where puzzle input is presented as a non-nullable (Kotlin) list of strings.
 *
 * @param <R> result type
 * @author Martijn van de Rijdt
 */
interface ListSolver<R>: LinesSolver<R> {
    override fun solve(lines: Stream<String>?): R = solve(lines!!.toList())

    /**
     * Solver method.
     *
     * @param lines list of strings, each of which corresponds to a line from the input
     * @return solution to the puzzle for the given input
     */
    fun solve(lines: List<String>): R
}