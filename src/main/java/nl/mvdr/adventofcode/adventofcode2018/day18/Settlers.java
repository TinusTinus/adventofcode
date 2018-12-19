package nl.mvdr.adventofcode.adventofcode2018.day18;

import java.io.IOException;
import java.nio.file.Path;

import nl.mvdr.adventofcode.PathSolver;

/**
 * Solution to the day 18 puzzle of 2018's Advent of Code:
 * <a href="https://adventofcode.com/2018/day/18">Settlers of the North Pole</a>.
 *
 * @author Martijn van de Rijdt
 */
public class Settlers implements PathSolver {

    @Override
    public String solve(Path inputFilePath) throws IOException {
        LumberCollectionArea area = LumberCollectionArea.parse(inputFilePath);

        // TODO
        System.out.println(area);
        
        return null;
    }

    /**
     * Main method.
     * 
     * @param args commandline arguments; these are ignored
     */
    public static void main(String[] args) {
        Settlers instance = new Settlers();

        String result = instance.solve("input-day18-2018.txt");

        System.out.println(result);
    }
}
