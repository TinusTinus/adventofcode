package nl.mvdr.adventofcode.adventofcode2017.day22;

import java.io.IOException;
import java.nio.file.Path;
import java.util.HashSet;
import java.util.Set;

import nl.mvdr.adventofcode.point.Direction;
import nl.mvdr.adventofcode.point.Point;

/**
 * Representation of the grid.
 *
 * @author Martijn van de Rijdt
 */
class Grid {
    private final Set<Point> infectedNodes;
    
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
        return new Grid(Set.of(), new Point(0, 0), Direction.UP, 0); // TODO implement for realsies
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
    private Grid(Set<Point> infectedNodes, Point carrierLocation, Direction carrierDirection, int infectionCount) {
        super();
        this.infectedNodes = infectedNodes;
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
    Grid burst(int bursts) {
        Grid result = this;
        for (int i = 0; i != bursts; i++) {
            result = result.burst();
        }
        return result;
    }
    
    /** @return new state of the grid, after executing a single burst */
    Grid burst() {
        Direction newDirection;
        Set<Point> newInfectedNodes = new HashSet<>(infectedNodes);
        int newInfectionCount = infectionCount;
        if (infectedNodes.contains(carrierLocation)) {
            // Carrier turns to its right.
            newDirection = carrierDirection.turnClockwise();
            // Curent node becomes cleaned.
            newInfectedNodes.remove(carrierLocation);
        } else {
            // Carrier turns to its left.
            newDirection = carrierDirection.turnCounterClockwise();
            // Current node becomes infected.
            newInfectedNodes.remove(carrierLocation);
            newInfectionCount++;
        }
        
        // Carrier moves forward one node in the direction it is now facing.
        Point newLocation = newDirection.move(carrierLocation);
        
        return new Grid(Set.copyOf(newInfectedNodes), newLocation, newDirection, newInfectionCount);
    }
    
    /**
     * @return number of times the carrier has infected a node
     *      (regardless of whether the node in question has since been cleaned)
     */
    int getInfectionCount() {
        return infectionCount;
    }
}
