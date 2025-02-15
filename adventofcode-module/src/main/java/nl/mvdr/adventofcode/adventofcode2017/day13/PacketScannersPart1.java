package nl.mvdr.adventofcode.adventofcode2017.day13;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nl.mvdr.adventofcode.solver.IntSolver;

/**
 * Solution to the day 13 puzzle of 2017's Advent of Code:
 * <a href="https://adventofcode.com/2017/day/13">Packet Scanners</a>.
 *
 * @author Martijn van de Rijdt
 */
public class PacketScannersPart1 implements IntSolver {

    private static final Logger LOGGER = LoggerFactory.getLogger(PacketScannersPart1.class);
    
    /**
     * {@inheritDoc}
     * 
     * @return severity of the whole trip
     */
    @Override
    public int solve(Stream<String> lines) {
        Set<Layer> layers = Layer.parse(lines);
        return computeSeverity(layers);
    }
    
    /**
     * Computes the severity if the packet were to depart right now.
     * 
     * @param initialLayers layers (including their current scanner positions and directions)
     * @return severity
     */
    private static int computeSeverity(Set<Layer> initialLayers) {
        Set<Layer> layers = initialLayers;
        int maxLayer = layers.stream()
                .mapToInt(Layer::getDepth)
                .max()
                .orElseThrow();
        
        int severity = 0;
        for (int packetLayer = 0; packetLayer <= maxLayer; packetLayer++) {
            int currentPacketLayer = packetLayer;
            severity = severity + layers.stream()
                    .filter(layer -> layer.getDepth() == currentPacketLayer)
                    .filter(layer -> layer.getScannerPosition() == 0)
                    .mapToInt(Layer::severity)
                    .sum();
            
            layers = layers.stream()
                    .map(Layer::tick)
                    .collect(Collectors.toSet());
        }
        
        return severity;
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
