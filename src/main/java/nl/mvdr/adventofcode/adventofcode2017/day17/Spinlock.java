package nl.mvdr.adventofcode.adventofcode2017.day17;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import nl.mvdr.adventofcode.PathSolver;

/**
 * Solution to the day 17 puzzle of 2017's Advent of Code:
 * <a href="https://adventofcode.com/2017/day/17">Spinlock</a>.
 *
 * @author Martijn van de Rijdt
 */
abstract class Spinlock implements PathSolver<Integer> {

    @Override
    public Integer solve(Path inputFilePath) throws IOException {
        int stepSize = Files.lines(inputFilePath)
                .mapToInt(Integer::parseInt)
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
    abstract Integer solve(int stepSize);
}
