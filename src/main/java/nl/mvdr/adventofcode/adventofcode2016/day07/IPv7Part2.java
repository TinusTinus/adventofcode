package nl.mvdr.adventofcode.adventofcode2016.day07;

import java.io.IOException;
import java.nio.file.Path;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nl.mvdr.adventofcode.PathSolver;

/**
 * Solution to the day 7 puzzle of 2016's Advent of Code:
 * <a href="https://adventofcode.com/2016/day/7">Internet Protocol Version 7</a>.
 *
 * @author Martijn van de Rijdt
 */
public class IPv7Part2 implements PathSolver<Long> {

    private static final Logger LOGGER = LoggerFactory.getLogger(IPv7Part2.class);
    
    /**
     * {@inheritDoc}
     * 
     * @return the number of IP addresses which support TLS
     */
    @Override
    public Long solve(Path inputFilePath) throws IOException {
        long result = IPAddress.parse(inputFilePath).stream()
                .filter(IPAddress::supportsSuperSecretListening)
                .count();
        
        return Long.valueOf(result);
    }
    
    /**
     * Main method.
     * 
     * @param args commandline arguments; these are ignored
     */
    public static void main(String[] args) {
        IPv7Part2 instance = new IPv7Part2();

        String result = instance.solve("input-day07-2016.txt");

        LOGGER.info(result);
    }
}
