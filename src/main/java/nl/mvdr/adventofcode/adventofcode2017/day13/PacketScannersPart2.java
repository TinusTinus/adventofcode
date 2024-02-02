package nl.mvdr.adventofcode.adventofcode2017.day13;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nl.mvdr.adventofcode.IntSolver;

/**
 * Solution to the day 13 puzzle of 2017's Advent of Code:
 * <a href="https://adventofcode.com/2017/day/13">Packet Scanners</a>.
 *
 * @author Martijn van de Rijdt
 */
public class PacketScannersPart2 implements IntSolver {

    private static final Logger LOGGER = LoggerFactory.getLogger(PacketScannersPart2.class);
    
    /**
     * {@inheritDoc}
     * 
     * @return fewest number of picoseconds that we need to delay the packet to pass through the firewall without being caught
     */
    @Override
    public int solve(Stream<String> lines) {
        Set<Layer> layers = Layer.parse(lines);
        
        int delay = 0;
        while (isCaught(layers)) {
            layers = tick(layers);
            delay++;
        }
        
        return delay;
    }

    /**
     * Determines whether we would be caught, if the packet were to depart right now.
     * 
     * @param initialLayers layers (including their current scanner positions and directions)
     * @return whether we'd get caught
     */
    private static boolean isCaught(Set<Layer> initialLayers) {
        Set<Layer> layers = initialLayers;
        int maxLayer = layers.stream()
                .mapToInt(Layer::getDepth)
                .max()
                .orElseThrow();
        
        boolean caught = false;
        
        int packetLayer = 0;
        while (!caught && packetLayer <= maxLayer) {
        
            int currentPacketLayer = packetLayer;
            
            caught = layers.stream()
                    .filter(layer -> layer.getDepth() == currentPacketLayer)
                    .anyMatch(layer -> layer.getScannerPosition() == 0);
            
            layers = tick(layers);
            
            packetLayer++;
        }
        
        return caught;
    }

    private static Set<Layer> tick(Set<Layer> layers) {
        return layers.stream()
                .map(Layer::tick)
                .collect(Collectors.toSet());
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
