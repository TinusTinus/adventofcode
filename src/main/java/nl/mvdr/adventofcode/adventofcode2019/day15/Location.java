package nl.mvdr.adventofcode.adventofcode2019.day15;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nl.mvdr.adventofcode.adventofcode2019.intcode.Program;
import nl.mvdr.adventofcode.point.Direction;
import nl.mvdr.adventofcode.point.Point;

/**
 * A location within the area.
 *
 * @author Martijn van de Rijdt
 */
class Location {
    
    private final Logger LOGGER = LoggerFactory.getLogger(Logger.class);
    
    /** Coordinates of the current location. */
    private final Point location;
    
    /** Program state after computing a path to this location. */
    private final Program program;
    
    /** Length of the shortest path from the starting point to this location. */
    private final int pathLength;
    
    /** Whether this location contains the oxygen system. */
    private final boolean oxygenSystem;

    /**
     * Constructor.
     * 
     * @param program initial program state
     */
    Location(Program program) {
        this(Point.ORIGIN, program, 0, false);
    }
    
    /**
     * Constructor.
     * 
     * @param location coordinates
     * @param program program state after computing a path to this location
     * @param pathLength length of the shortest path from the starting point to this location
     * @param oxygenSystem whether the location contains the oxygen system
     */
    private Location(Point location, Program program, int pathLength, boolean oxygenSystem) {
        super();
        this.location = location;
        this.program = program;
        this.pathLength = pathLength;
        this.oxygenSystem = oxygenSystem;
    }
    
    /** @return location of the oxygen system */
    Location moveToOxygenSystem() {
        // Use Dijkstra's shortest path algorithm to find a shortest path to the oxygen system.
        // Visited and unvisited nodes are indexed by their locations.
        Map<Point, Location> visited = new HashMap<>();
        Map<Point, Location> unvisited = new HashMap<>();
        Location current = this;
        
        while (!current.oxygenSystem) {
            for (Direction direction : Direction.values()) {
                if (!visited.containsKey(direction.move(current.getLocation()))) {
                    current.step(direction)
                            .filter(next -> !unvisited.containsKey(next.getLocation()) || next.getPathLength() < unvisited.get(next.getLocation()).getPathLength())
                            .ifPresent(next -> unvisited.put(next.getLocation(), next));
                }
            }
            
            unvisited.remove(current.getLocation());
            visited.put(current.getLocation(), current);
            
            current = unvisited.values().stream()
                    .min(Comparator.comparing(Location::getPathLength))
                    .orElseThrow();
        }
        
        LOGGER.debug("Oxygen system found at {}", current.getLocation());
        
        return current;
    }
    
    /**
     * Attempts to take a single step in the given direction.
     * 
     * @param direction direction to step to
     * @return updated state of this object, or empty in case a wall blocks movement into the given direction
     */
    Optional<Location> step(Direction direction) {
        ProgramAndValue programAndValue = new ProgramAndValue(program, direction.getCode())
                .performInputAndOutput();
        Tile tile = Tile.of(programAndValue.getValue());
        
        Optional<Location> result;
        if (tile == Tile.WALL) {
            result = Optional.empty();
        } else {
            Location droid = new Location(direction.move(location), programAndValue.getProgram(), pathLength + 1,
                    tile == Tile.OXYGEN_SYSTEM);
            result = Optional.of(droid);
        }
        return result;
    }
    
    Point getLocation() {
        return location;
    }
    
    int getPathLength() {
        return pathLength;
    }
    
    public Program getProgram() {
        return program;
    }
}
