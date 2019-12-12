package nl.mvdr.adventofcode.adventofcode2018.day22;

import java.util.stream.Stream;

import nl.mvdr.adventofcode.IntSolver;

/**
 * Solution to the day 22 puzzle of 2018's Advent of Code:
 * <a href="https://adventofcode.com/2018/day/22">Mode Maze</a>.
 *
 * @author Martijn van de Rijdt
 */
abstract class ModeMaze implements IntSolver {
    
    @Override
    public int solve(Stream<String> lines) {
        return solve(Cave.parse(lines));
    }

    /**
     * Solver method.
     * 
     * @param cave the cave
     * @return integer representing the puzzle's result
     */
    protected abstract int solve(Cave cave);
}
