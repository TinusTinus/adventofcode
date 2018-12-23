package nl.mvdr.adventofcode.adventofcode2018.day04;

import java.io.IOException;
import java.nio.file.Path;

import nl.mvdr.adventofcode.PathSolver;

/**
 * Solution to the day 4 puzzle of 2018's Advent of Code:
 * <a href="https://adventofcode.com/2018/day/4">Repose Record</a>.
 *
 * @author Martijn van de Rijdt
 */
public class ReposeRecord implements PathSolver {

    @Override
    public String solve(Path inputFilePath) throws IOException {
        // TODO implement
        return null;
    }

        /**
     * Main method.
     * 
     * @param args commandline arguments; these are ignored
     */
    public static void main(String[] args) {
        ReposeRecord instance = new ReposeRecord();

        String result = instance.solve("input-day04-2018.txt");

        System.out.println(result);
    }
}
