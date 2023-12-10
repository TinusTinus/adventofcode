package nl.mvdr.adventofcode.adventofcode2023.day10;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import nl.mvdr.adventofcode.point.Direction;
import nl.mvdr.adventofcode.point.Point;

/**
 * TODO javadoc
 *
 * @author Martijn van de Rijdt
 */
enum Pipe {
    /**
     * | is a vertical pipe connecting north and south.
     */
    NORTH_SOUTH('|',
            Set.of(Direction.UP, Direction.DOWN),
            Set.of(
                    Set.of(Direction.LEFT, Direction.DOWN_LEFT, Direction.UP_LEFT),
                    Set.of(Direction.RIGHT, Direction.DOWN_RIGHT, Direction.UP_RIGHT))),
    /**
     * - is a horizontal pipe connecting east and west.
     */
    WEST_EAST('-',
            Set.of(Direction.LEFT, Direction.RIGHT),
            Set.of(
                    Set.of(Direction.UP, Direction.UP_LEFT, Direction.UP_RIGHT),
                    Set.of(Direction.DOWN, Direction.DOWN_LEFT, Direction.DOWN_RIGHT))),
    /**
     * L is a 90-degree bend connecting north and east.
     */
    NORTH_EAST('L',
            Set.of(Direction.UP, Direction.RIGHT),
            Set.of(
                    Set.of(Direction.UP_RIGHT),
                    Set.of(Direction.UP_LEFT, Direction.LEFT, Direction.DOWN_LEFT, Direction.DOWN, Direction.DOWN_RIGHT))),
    /**
     * J is a 90-degree bend connecting north and west.
     */
    NORTH_WEST('J',
            Set.of(Direction.UP, Direction.LEFT),
            Set.of(
                    Set.of(Direction.UP_LEFT),
                    Set.of(Direction.UP_RIGHT, Direction.RIGHT, Direction.DOWN_RIGHT, Direction.DOWN, Direction.DOWN_LEFT))),
    /**
     * 7 is a 90-degree bend connecting south and west.
     */
    SOUTH_WEST('7',
            Set.of(Direction.DOWN, Direction.LEFT),
            Set.of(
                    Set.of(Direction.DOWN_LEFT),
                    Set.of(Direction.DOWN_RIGHT, Direction.RIGHT, Direction.UP_RIGHT, Direction.UP, Direction.UP_LEFT))),
    /**
     * F is a 90-degree bend connecting south and east.
     */
    SOUTH_EAST('F',
            Set.of(Direction.DOWN, Direction.RIGHT),
            Set.of(
                    Set.of(Direction.DOWN_RIGHT),
                    Set.of(Direction.UP_RIGHT, Direction.UP, Direction.UP_LEFT, Direction.LEFT, Direction.DOWN_LEFT)));
    
    private final char representation;
    private final Set<Direction> connections;
    private final Set<Set<Direction>> sides;
    
    /**
     * Constructor.
     * 
     * @param representation the single-character representation of this pipe in the puzzle input
     * @param connections the (exactly two) directions connected by this pipe, where {@link Direction#UP} represents north
     * @param sides the (exactly two) sides of this pipe, as two sets of directions (including diagonals!)
     */
    Pipe(char representation, Set<Direction> connections, Set<Set<Direction>> sides) {
        this.representation = representation;
        this.connections = connections;
        this.sides = sides;
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
     * @return the (exactly two) directions connected by this pipe, where {@link Direction#UP} represents north
     */
    Set<Direction> getConnections() {
        return connections;
    }
    
    /**
     * Returns the (exactly two) sides of this pipe.
     * 
     * @param location location of this pipe in the maze
     * @return sides, as two sets of neighbouring points of this pipe (including diagonals!)
     */
    Set<Set<Point>> getSides(Point location) {
        return sides.stream()
                .map(side -> side.stream()
                        .map(direction -> direction.move(location))
                        .collect(Collectors.toSet()))
                .collect(Collectors.toSet());
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
