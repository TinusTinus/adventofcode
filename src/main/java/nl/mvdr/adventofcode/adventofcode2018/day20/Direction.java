package nl.mvdr.adventofcode.adventofcode2018.day20;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import nl.mvdr.adventofcode.adventofcode2018.point.Point;

/**
 * Direction.
 *
 * @author Martijn van de Rijdt
 */
public enum Direction implements RoomMapExpression {
    
    /** North. */
    NORTH((points, map) -> points.stream()
            .map(Point::northNeighbour)
            .peek(point -> map.getRooms().putIfAbsent(point, new Room()).addSouthDoor())
            .collect(Collectors.toSet())),
    
    /** East. */
    EAST((points, map) -> points.stream()
                .peek(point -> map.getRooms().putIfAbsent(point, new Room()).addEastDoor())
                .map(Point::eastNeighbour)
                .collect(Collectors.toSet())),
    
    /** South. */
    SOUTH((points, map) -> points.stream()
            .peek(point -> map.getRooms().putIfAbsent(point, new Room()).addSouthDoor())
            .map(Point::southNeighbour)
            .collect(Collectors.toSet())),
    
    /** West. */
    WEST((points, map) -> points.stream()
            .map(Point::westNeighbour)
            .peek(point -> map.getRooms().putIfAbsent(point, new Room()).addEastDoor())
            .collect(Collectors.toSet()));
    
    private final RoomMapExpression applyFunction;
    
    Direction(RoomMapExpression applyFunction) {
        this.applyFunction = applyFunction;
    }
    
    /** @return single-character representation of this direction */
    char getCharacterRepresentation() {
        return name().charAt(0);
    }
    
    /**
     * Parses the given character into a direction.
     * 
     * @param c character representation of a direction: N, E, S or W
     * @return direction
     */
    static Direction parse(char c) {
        return Stream.of(Direction.values())
                .filter(value -> value.getCharacterRepresentation() == c)
                .findFirst()
                .get();
    }
    
    @Override
    public Set<Point> apply(Set<Point> points, RoomMap map) {
        return applyFunction.apply(points, map);
    }
    
    @Override
    public String toString() {
        return "" + getCharacterRepresentation();
    }
}
