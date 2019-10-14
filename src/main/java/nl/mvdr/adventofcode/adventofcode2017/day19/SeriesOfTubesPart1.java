package nl.mvdr.adventofcode.adventofcode2017.day19;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nl.mvdr.adventofcode.PathSolver;

/**
 * Solution to the day 19 puzzle of 2017's Advent of Code:
 * <a href="https://adventofcode.com/2017/day/19">A Series of Tubes</a>.
 *
 * @author Martijn van de Rijdt
 */
public class SeriesOfTubesPart1 implements PathSolver<String> {

    private static final Logger LOGGER = LoggerFactory.getLogger(SeriesOfTubesPart1.class);
    
    /**
     * {@inheritDoc}
     * 
     * @return the letters the packet sees on its path
     */
    @Override
    public String solve(Path inputFilePath) throws IOException {
        List<String> map = Files.lines(inputFilePath)
                .collect(Collectors.toList());
        
        String topLine = map.get(0);
        int startX = IntStream.range(0, topLine.length())
                .filter(i -> topLine.charAt(i) == '|')
                .findFirst()
                .getAsInt();
        
        // TODO
        
        return null;
    }
    
    /**
     * Main method.
     * 
     * @param args commandline arguments; these are ignored
     */
    public static void main(String[] args) {
        SeriesOfTubesPart1 instance = new SeriesOfTubesPart1();

        String result = instance.solve("input-day19-2017.txt");

        LOGGER.info(result);
    }
}
