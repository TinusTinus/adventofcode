package nl.mvdr.adventofcode.solver;

import java.util.stream.Stream;

/**
 * Convenience interface, where puzzle input is presented as a stream of strings,
 * and the result value is a {@code long}.
 * 
 * @author Martijn van de Rijdt
 */
@FunctionalInterface
public interface LongSolver extends Solver {

    @Override
    default String solve(String inputfile) {
        LinesSolver<Long> linesSolver = lines -> Long.valueOf(solve(lines));
        return linesSolver.solve(inputfile);
    }

    /**
     * Solver method.
     * 
     * Note that the last line from the input is usually empty (input files typically end with a newline).
     * 
     * @param lines stream of strings, each of which corresponds to a line from the input
     * @return solution to the puzzle for the given input
     */
    long solve(Stream<String> lines);
}
