package nl.mvdr.adventofcode.adventofcode2017.day22;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nl.mvdr.adventofcode.point.Direction;
import nl.mvdr.adventofcode.point.Point;

/**
 * Representation of the grid.
 * 
 * This class is NOT immutable (for performance reasons: copying the sets of nodes is an expensive operation).
 *
 * @author Martijn van de Rijdt
 */
class Grid {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(Grid.class);
    
    private final Set<Point> infectedNodes;
    private final Set<Point> weakenedNodes;
    private final Set<Point> flaggedNodes;
    
    private Point carrierLocation;
    private Direction carrierDirection;
    
    private int infectionCount;

    /**
     * Creates a new grid, based on the contents of the text file at the given path.
     * 
     * @param inputFilePath path to a text file containing puzzle input
     * @return grid
     * @throws IOException in case the text file could not be read
     */
    static Grid parse(Path inputFilePath) throws IOException {
        List<String> lines = Files.lines(inputFilePath)
                .filter(line -> !"".equals(line))
                .collect(Collectors.toList());

        int startingX = lines.size() / 2;
        int startingY = lines.get(startingX).length() / 2;
        Point startingLocation = new Point(startingX, startingY);
        
        Set<Point> infectedNodes = IntStream.range(0, lines.size())
                .boxed()
                .flatMap(y -> IntStream.range(0, lines.get(y.intValue()).length())
                        .filter(x -> lines.get(y.intValue()).charAt(x) == '#')
                        .mapToObj(x -> new Point(x, y.intValue())))
                .collect(Collectors.toSet());
        
        return new Grid(Set.copyOf(infectedNodes), startingLocation);
    }
    
    /**
     * Constructor.
     * 
     * @param infectedNodes nodes which are currently infected
     * @param infectionCount number of times the carrier has infected a node
     *      (regardless of whether the node in question has since been cleaned)
     */
    private Grid(Set<Point> infectedNodes, Point carrierLocation) {
        super();
        this.infectedNodes = new HashSet<>(infectedNodes);
        this.weakenedNodes = new HashSet<>();
        this.flaggedNodes = new HashSet<>();
        this.carrierLocation = carrierLocation;
        this.carrierDirection = Direction.UP;
        this.infectionCount = 0;
    }
    
    /**
     * Performs the given number of bursts.
     * 
     * @param bursts number of times to burst
     * @param evolved whether the virus has evolved
     */
    void burst(int bursts, boolean evolved) {
        LOGGER.debug("Bursting {} times. Initial grid: {}", Integer.valueOf(bursts), this);
        for (int i = 0; i != bursts; i++) {
            burst(evolved);
            LOGGER.debug("Updated grid: {}", this);
        }
    }
    
    /**
     * Performs a single burst.
     * 
     * @param evolved whether the virus has evolved
     */
    private void burst(boolean evolved) {
        if (infectedNodes.contains(carrierLocation)) {
            LOGGER.debug("Node at {} is infected.", carrierLocation);
            // Carrier turns to its right.
            carrierDirection = carrierDirection.turnClockwise();
            
            // Current node is no longer infected.
            infectedNodes.remove(carrierLocation);
            if (evolved) {
                // Curent node becomes flagged.
                flaggedNodes.add(carrierLocation);
            } // If not evolved: current node becomes clean.
        } else if (weakenedNodes.contains(carrierLocation)) {
            LOGGER.debug("Node at {} is weakened.", carrierLocation);
            // Carrier does not turn.
            // Current node becomes infected.
            weakenedNodes.remove(carrierLocation);
            infectedNodes.add(carrierLocation);
            infectionCount++;
        } else if (flaggedNodes.contains(carrierLocation)) {
            // Carrier reverses direction.
            carrierDirection = carrierDirection.reverse();
            // Current node becomes clean.
            flaggedNodes.remove(carrierLocation);
        } else {
            LOGGER.debug("Node at {} is clean.", carrierLocation);
            // Carrier turns to its left.
            carrierDirection = carrierDirection.turnCounterClockwise();
            if (evolved) {
                // Current node becomes weakened.
                weakenedNodes.add(carrierLocation);
            } else {
                // Current node becomes infected.
                infectedNodes.add(carrierLocation);
                infectionCount++;
            }
        }
        
        // Carrier moves forward one node in the direction it is now facing.
        carrierLocation = carrierDirection.move(carrierLocation);
    }
    
    /**
     * @return number of times the carrier has infected a node
     *      (regardless of whether the node in question has since been cleaned)
     */
    int getInfectionCount() {
        return infectionCount;
    }

    @Override
    public String toString() {
        return "Grid [infectedNodes=" + infectedNodes + ", weakenedNodes=" + weakenedNodes + ", flaggedNodes="
                + flaggedNodes + ", carrierLocation=" + carrierLocation + ", carrierDirection=" + carrierDirection
                + ", infectionCount=" + infectionCount + "]";
    }
}
