package nl.mvdr.adventofcode.adventofcode2017.day13;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * A layer of the firewall.
 *
 * @author Martijn van de Rijdt
 */
class Layer {
    /** The depth of this layer within the firewall. */
    private final int depth;
    
    /** Range of the scanner within this layer. */
    private final int range;
    
    /** Current position of the scanner. */
    private final int scannerPosition;
    
    /** Current direction in which the scanner is moving. */
    private final ScannerDirection scannerDirection;

    /**
     * Parses the puzzle input into a set of layers.
     * 
     * @param inputFilePath path to the text file containing puzzle input
     * @return set of layers
     * @throws IOException exception while attempting to read the puzzle input
     */
    static Set<Layer> parse(Path inputFilePath) throws IOException {
        return Files.lines(inputFilePath)
                // ignore empty lines (the last line in the file)
                .filter(Objects::nonNull)
                .filter(line -> !line.isBlank())
                // parse each line
                .map(Layer::parseLine)
                .collect(Collectors.toSet());
    }
    
    /**
     * Parses a single line from the puzzle input into a layer, with the scanner at its starting position.
     * 
     * @param line line
     * @return new layer
     */
    private static Layer parseLine(String line) {
        String[] parts = line.split(": ");
        
        int depth = Integer.parseInt(parts[0]);
        int range = Integer.parseInt(parts[1]);
        
        return new Layer(depth, range, 0, ScannerDirection.FORTH);
    }
    
    /**
     * Computes the severity if the packet were to depart right now.
     * 
     * @param initialLayers layers (including their current scanner positions and directions)
     * @return severity
     */
    static int computeSeverity(Set<Layer> initialLayers) {
        Set<Layer> layers = initialLayers;
        int maxLayer = layers.stream()
                .mapToInt(Layer::getDepth)
                .max()
                .getAsInt();
        
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
     * Constructor.
     * 
     * @param depth depth of this layer within the firewall
     * @param range range of the scanner within this layer
     * @param scannerPosition current position of the scanner
     * @param scannerDirection current direction in which the scanner is moving
     */
    private Layer(int depth, int range, int scannerPosition, ScannerDirection scannerDirection) {
        super();
        this.depth = depth;
        this.range = range;
        this.scannerPosition = scannerPosition;
        this.scannerDirection = scannerDirection;
    }
    
    /** @return updated layer after a picosecond has passed */
    Layer tick() {
        ScannerDirection nextScannerDirection;
        int nextScannerPosition = scannerDirection.apply(scannerPosition);
        
        if (0 <= nextScannerPosition && nextScannerPosition < range) {
            // ok
            nextScannerDirection = scannerDirection;
        } else {
            // would be out of bounds, flip the direction
            nextScannerDirection = scannerDirection.other();
            nextScannerPosition = nextScannerDirection.apply(scannerPosition);
        }
        
        return new Layer(depth, range, nextScannerPosition, nextScannerDirection);
    }
    
    int getDepth() {
        return depth;
    }
    
    int getScannerPosition() {
        return scannerPosition;
    }
    
    /** @return severity of this layer */
    int severity() {
        return depth * range;
    }
}
