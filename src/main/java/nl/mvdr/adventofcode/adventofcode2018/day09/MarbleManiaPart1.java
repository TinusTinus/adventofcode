package nl.mvdr.adventofcode.adventofcode2018.day09;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nl.mvdr.adventofcode.PathSolver;

/**
 * Solution to part 1 of the day 9 puzzle of 2018's Advent of Code:
 * <a href="https://adventofcode.com/2018/day/9">Marble Mania</a>.
 *
 * @author Martijn van de Rijdt
 */
public class MarbleManiaPart1 implements PathSolver {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(MarbleManiaPart1.class);
    
    private static final Pattern PATTERN = Pattern.compile("(\\d+) players; last marble is worth (\\d+) points");
    
    @Override
    public String solve(Path inputFilePath) throws IOException {
        PuzzleInput puzzleInput = PuzzleInput.parse(inputFilePath);
        
        // TODO
        return null;
    }
    
    /**
     * Main method.
     * 
     * @param args commandline arguments; these are ignored
     */
    public static void main(String[] args) {
        MarbleManiaPart1 instance = new MarbleManiaPart1();

        String result = instance.solve("input-day09-2018.txt");

        LOGGER.info(result);
    }
}
