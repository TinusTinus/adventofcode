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
 * @author Martijn van de Rijdt
 */
class Grid {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(Grid.class);
    
    private final Set<Point> infectedNodes;
    private final Set<Point> weakenedNodes;
    private final Set<Point> flaggedNodes;
    
    private final Point carrierLocation;
    private final Direction carrierDirection;
    
    private final int infectionCount;

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
        
        return new Grid(Set.copyOf(infectedNodes), Set.of(), Set.of(), startingLocation, Direction.UP, 0);
    }
    
    /**
     * Constructor.
     * 
     * @param infectedNodes nodes which are currently infected
     * @param carrierLocation current location of the carrier
     * @param carrierDirection current direction of the carrier
     * @param infectionCount number of times the carrier has infected a node
     *      (regardless of whether the node in question has since been cleaned)
     */
    private Grid(Set<Point> infectedNodes, Set<Point> weakenedNodes, Set<Point> flaggedNodes,
            Point carrierLocation, Direction carrierDirection, int infectionCount) {
        super();
        this.infectedNodes = infectedNodes;
        this.weakenedNodes = weakenedNodes;
        this.flaggedNodes = flaggedNodes;
        this.carrierLocation = carrierLocation;
        this.carrierDirection = carrierDirection;
        this.infectionCount = infectionCount;
    }
    
    /**
     * Performs the given number of bursts.
     * 
     * @param bursts number of times to burst
     * @return new state of the grid, after executing the given number of bursts
     */
    Grid burst(int bursts, boolean evolved) {
        LOGGER.debug("Bursting {} times. Initial grid: {}", Integer.valueOf(bursts), this);
        Grid result = this;
        for (int i = 0; i != bursts; i++) {
            if (evolved) {
                result = result.evolvedBurst();
            } else {
                result = result.burst();
            }
            LOGGER.debug("Updated grid: {}", result);
        }
        return result;
    }
    
    /** @return new state of the grid, after executing a single burst for the original virus */
    private Grid burst() {
        Direction newDirection;
        Set<Point> newInfectedNodes = new HashSet<>(infectedNodes);
        int newInfectionCount = infectionCount;
        if (infectedNodes.contains(carrierLocation)) {
            LOGGER.debug("Node at {} is infected. Carrier turns to its right and cleans the node.", carrierLocation);
            // Carrier turns to its right.
            newDirection = carrierDirection.turnClockwise();
            // Curent node becomes cleaned.
            newInfectedNodes.remove(carrierLocation);
        } else {
            LOGGER.debug("Node at {} is clean. Carrier turns to its left and infects the node.", carrierLocation);
            // Carrier turns to its left.
            newDirection = carrierDirection.turnCounterClockwise();
            // Current node becomes infected.
            newInfectedNodes.add(carrierLocation);
            newInfectionCount++;
        }
        
        // Carrier moves forward one node in the direction it is now facing.
        Point newLocation = newDirection.move(carrierLocation);
        
        return new Grid(Set.copyOf(newInfectedNodes), weakenedNodes, flaggedNodes, newLocation, newDirection, newInfectionCount);
    }
    
    /** @return new state of the grid, after executing a single burst for the evolved virus */
    private Grid evolvedBurst() {
        return this; // TODO implement
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
        return "Grid [infectedNodes=" + infectedNodes + ", carrierLocation=" + carrierLocation + ", carrierDirection="
                + carrierDirection + ", infectionCount=" + infectionCount + "]";
    }
}
