package nl.mvdr.adventofcode.adventofcode2017.day13;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nl.mvdr.adventofcode.PathSolver;

/**
 * Solution to the day 12 puzzle of 2017's Advent of Code:
 * <a href="https://adventofcode.com/2017/day/13">Packet Scanners</a>.
 *
 * @author Martijn van de Rijdt
 */
public class PacketScannersPart1 implements PathSolver<Integer> {

    private static final Logger LOGGER = LoggerFactory.getLogger(PacketScannersPart1.class);
    
    /**
     * {@inheritDoc}
     * 
     * @return severity of the whole trip
     */
    @Override
    public Integer solve(Path inputFilePath) throws IOException {
        Set<Layer> layers = Layer.parse(inputFilePath);
        int severity = Layer.computeSeverity(layers);
        return Integer.valueOf(severity);
    }

    /**
     * Main method.
     * 
     * @param args commandline arguments; these are ignored
     */
    public static void main(String[] args) {
        PacketScannersPart1 instance = new PacketScannersPart1();

        String result = instance.solve("input-day13-2017.txt");

        LOGGER.info(result);
    }
}
