package nl.mvdr.adventofcode;

import java.util.stream.Stream;

/**
 * Convenience interface, where puzzle input is presented as a stream of strings,
 * and the result value is an {@code int}.
 * 
 * @author Martijn van de Rijdt
 */
@FunctionalInterface
public interface IntSolver extends Solver {

    @Override
    default String solve(String inputfile) {
        LinesSolver<Integer> linesSolver = lines -> Integer.valueOf(solve(lines));
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
    int solve(Stream<String> lines);
}
