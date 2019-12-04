package nl.mvdr.adventofcode.adventofcode2017.day17;

import java.util.stream.Stream;

import nl.mvdr.adventofcode.IntSolver;

/**
 * Solution to the day 17 puzzle of 2017's Advent of Code:
 * <a href="https://adventofcode.com/2017/day/17">Spinlock</a>.
 *
 * @author Martijn van de Rijdt
 */
abstract class Spinlock implements IntSolver {

    @Override
    public int solve(Stream<String> lines) {
        int stepSize = lines.mapToInt(Integer::parseInt)
                .findFirst()
                .getAsInt();
        
        return solve(stepSize);
    }

    /**
     * Solver method.
     * 
     * @param stepSize number of steps between insertions of new values into the buffer
     * @return solution to the puzzle
     */
    abstract int    solve(int stepSize);
}
