package nl.mvdr.adventofcode.adventofcode2023.day10;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import nl.mvdr.adventofcode.point.Direction;

/**
 * TODO javadoc
 *
 * @author Martijn van de Rijdt
 */
enum Pipe {
    /**
     * | is a vertical pipe connecting north and south.
     */
    NORTH_SOUTH('|', Set.of(Direction.UP, Direction.DOWN)),
    /**
     * - is a horizontal pipe connecting east and west.
     */
    WEST_EAST('-', Set.of(Direction.LEFT, Direction.RIGHT)),
    /**
     * L is a 90-degree bend connecting north and east.
     */
    NORTH_EAST('L', Set.of(Direction.UP, Direction.RIGHT)),
    /**
     * J is a 90-degree bend connecting north and west.
     */
    NORTH_WEST('J', Set.of(Direction.UP, Direction.LEFT)),
    /**
     * 7 is a 90-degree bend connecting south and west.
     */
    SOUTH_WEST('7', Set.of(Direction.DOWN, Direction.LEFT)),
    /**
     * F is a 90-degree bend connecting south and east.
     */
    SOUTH_EAST('F', Set.of(Direction.DOWN, Direction.RIGHT));
    
    final char representation;
    final Set<Direction> connections;
    
    /**
     * Constructor.
     * 
     * @param representation the single-character representation of this pipe in the puzzle input
     * @param connections the (exactly two) directions connected by this pipe, where {@link Direction#UP} represents north
     */
    Pipe(char representation, Set<Direction> connections) {
        this.representation = representation;
        this.connections = connections;
    }
    
    /**
     * Whether this pipe connects to the given direction.
     * 
     * @param direction direction
     * @return whether the given direction is a connection
     */
    boolean connectsTo(Direction direction) {
        return connections.contains(direction);
    }
    
    /**
     * @return connections
     */
    Set<Direction> getConnections() {
        return connections;
    }
    
    /**
     * Finds the next direction, given that this pipe was entered <em>from</em> the given direction.
     * 
     * @param previousDirection previous direction
     * @return next direction
     */
    Direction findNextDirection(Direction previousDirection) {
        var result = connections.stream()
                .filter(connection -> previousDirection.reverse() != connection)
                .collect(Collectors.toSet());
        if (result.size() != 1) {
            throw new IllegalArgumentException(this + " does not connect to direction " + previousDirection.reverse());
        }
        return result.iterator().next();
    }
    
    /**
     * Parses a character from the puzzle input.
     * 
     * @param representation representation of a pipe
     * @return pipe
     */
    static Pipe parse(char representation) {
        return Stream.of(values())
                .filter(value -> value.representation == representation)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Not a pipe: " + representation));
    }
    
    /**
     * Finds the pipe which connects the given directions.
     * 
     * @param connections set of (two) directions
     * @return the pipe connecting the given directions
     */
    static Pipe of(Set<Direction> connections) {
        return Stream.of(values())
                .filter(value -> value.connections.equals(connections))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("No pipes connecting: " + connections));
    }
}
