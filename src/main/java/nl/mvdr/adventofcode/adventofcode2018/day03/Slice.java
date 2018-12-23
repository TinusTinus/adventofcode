package nl.mvdr.adventofcode.adventofcode2018.day03;

import java.io.IOException;
import java.nio.file.Path;

import nl.mvdr.adventofcode.PathSolver;

/**
 * Solution to the day 3 puzzle of 2018's Advent of Code:
 * <a href="https://adventofcode.com/2018/day/3">No Matter How You Slice It</a>.
 *
 * @author Martijn van de Rijdt
 */
public class Slice implements PathSolver {

    @Override
    public String solve(Path inputFilePath) throws IOException {
        // TODO
        return null;
    }

    /**
     * Main method.
     * 
     * @param args commandline arguments; these are ignored
     */
    public static void main(String[] args) {
        Slice instance = new Slice();

        String result = instance.solve("input-day03-2018.txt");

        System.out.println(result);
    }
}
