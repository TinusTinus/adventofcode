package nl.mvdr.adventofcode.adventofcode2018.day13;

import java.io.IOException;
import java.nio.file.Path;

import nl.mvdr.adventofcode.PathSolver;

/**
 * Solution to the day 13 puzzle of 2018's Advent of Code:
 * <a href="https://adventofcode.com/2018/day/13">Mine Cart Madness</a>.
 *
 * Soundtrack for this puzzle:
 * <a href="https://www.youtube.com/watch?v=PVPqxuebYQw">Mine Cart Madness</a>
 * by <a href="https://en.wikipedia.org/wiki/David_Wise_(composer)">David
 * Wise</a>.
 *
 * @author Martijn van de Rijdt
 */
abstract class MineCartMadness implements PathSolver<String> {

    @Override
    public String solve(Path inputFilePath) throws IOException {
        State state = State.parse(inputFilePath);

        return solve(state);
    }
    
    /**
     * Solver method.
     * 
     * @param state initial state
     * @return solution to the puzzle for the given input
     * @throws IOException in case the file cannot be read
     */
    protected abstract String solve(State state);
}