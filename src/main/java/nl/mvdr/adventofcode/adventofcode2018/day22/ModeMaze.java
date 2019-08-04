package nl.mvdr.adventofcode.adventofcode2018.day22;

import java.io.IOException;
import java.nio.file.Path;

import nl.mvdr.adventofcode.PathSolver;

/**
 * Solution to the day 22 puzzle of 2018's Advent of Code:
 * <a href="https://adventofcode.com/2018/day/22">Mode Maze</a>.
 *
 * @author Martijn van de Rijdt
 */
abstract class ModeMaze implements PathSolver<Integer> {
    
    @Override
    public Integer solve(Path inputFilePath) throws IOException {
        Cave cave = Cave.parse(inputFilePath);
        int result = solve(cave);
        return Integer.valueOf(result);
    }

    /**
     * Solver method.
     * 
     * @param cave the cave
     * @return integer representing the puzzle's result
     */
    protected abstract int solve(Cave cave);
}
