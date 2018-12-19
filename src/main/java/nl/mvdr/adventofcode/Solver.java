package nl.mvdr.adventofcode;

/**
 * Solver interface.
 * 
 * Each Advent of Code puzzle seems to take an input file and deliver a String
 * answer.
 *
 * @author Martijn van de Rijdt
 */
@FunctionalInterface
public interface Solver {
    /**
     * Solver method.
     * 
     * @param filename filename of the input file (on the classpath)
     * @return solution to the puzzle for the given input
     */
    String solve(String inputfile);
}
