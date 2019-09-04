package nl.mvdr.adventofcode.adventofcode2017.day13;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Set;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nl.mvdr.adventofcode.PathSolver;

/**
 * Solution to the day 12 puzzle of 2017's Advent of Code:
 * <a href="https://adventofcode.com/2017/day/13">Packet Scanners</a>.
 *
 * @author Martijn van de Rijdt
 */
public class PacketScannersPart2 implements PathSolver<Integer> {

    private static final Logger LOGGER = LoggerFactory.getLogger(PacketScannersPart2.class);
    
    /**
     * {@inheritDoc}
     * 
     * @return fewest number of picoseconds that we need to delay the packet to pass through the firewall without being caught
     */
    @Override
    public Integer solve(Path inputFilePath) throws IOException {
        Set<Layer> layers = Layer.parse(inputFilePath);
        
        int delay = 0;
        while (Layer.computeSeverity(layers) != 0) {
            layers = layers.stream()
                    .map(Layer::tick)
                    .collect(Collectors.toSet());
            delay++;
        }
        
        return Integer.valueOf(delay);
    }

    /**
     * Main method.
     * 
     * @param args commandline arguments; these are ignored
     */
    public static void main(String[] args) {
        PacketScannersPart2 instance = new PacketScannersPart2();

        String result = instance.solve("input-day13-2017.txt");

        LOGGER.info(result);
    }
}
