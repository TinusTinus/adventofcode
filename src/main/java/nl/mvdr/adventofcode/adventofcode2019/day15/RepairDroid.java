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
 * Current state of the repair droid.
 *
 * @author Martijn van de Rijdt
 */
class RepairDroid {
    
    private final Logger LOGGER = LoggerFactory.getLogger(Logger.class);
    
    /** Current location of this droid. */
    private final Point location;
    
    /** Program state after computing a path to this location. */
    private final Program program;
    
    /** Length of the path from the starting point to this location. */
    private final int pathLength;
    
    /** Whether the droid's location is the oxygen system. */
    private final boolean atOxygenSystem;

    /**
     * Constructor.
     * 
     * @param program initial program state
     */
    RepairDroid(Program program) {
        this(new Point(0, 0), program, 0, false);
    }
    
    /**
     * Constructor.
     * 
     * @param location current location of the droid
     * @param program program state after computing a path to this location
     * @param pathLength length of the path from the starting point to this location
     * @param atOxygenSystem whether the location is the oxygen system
     */
    private RepairDroid(Point location, Program program, int pathLength, boolean atOxygenSystem) {
        super();
        this.location = location;
        this.program = program;
        this.pathLength = pathLength;
        this.atOxygenSystem = atOxygenSystem;
    }
    
    RepairDroid moveToOxygenSystem() {
        // Use Dijkstra's shortest path algorithm to find a shortest path from the droid's current location to the oxygen system.
        // Visited and unvisited nodes are indexed by their locations.
        Map<Point, RepairDroid> visited = new HashMap<>();
        Map<Point, RepairDroid> unvisited = new HashMap<>();
        RepairDroid current = this;
        
        while (!current.isAtOxygenSystem()) {
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
                    .min(Comparator.comparing(RepairDroid::getPathLength))
                    .orElseThrow();
        }
        
        LOGGER.debug("Oxygen system found at {}", current.getLocation());
        
        return current;
    }
    
    /**
     * Attempts to take a single step in the given direction.
     * 
     * @param direction direction to step to
     * @return updated state of the droid, or empty in case the droid runs into a wall
     */
    Optional<RepairDroid> step(Direction direction) {
        ProgramAndValue programAndValue = new ProgramAndValue(program, direction.getCode())
                .performInputAndOutput();
        Tile tile = Tile.of(programAndValue.getValue());
        
        Optional<RepairDroid> result;
        if (tile == Tile.WALL) {
            result = Optional.empty();
        } else {
            RepairDroid droid = new RepairDroid(direction.move(location), programAndValue.getProgram(), pathLength + 1,
                    tile == Tile.OXYGEN_SYSTEM);
            result = Optional.of(droid);
        }
        return result;
    }
    
    boolean isAtOxygenSystem() {
        return atOxygenSystem;
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
