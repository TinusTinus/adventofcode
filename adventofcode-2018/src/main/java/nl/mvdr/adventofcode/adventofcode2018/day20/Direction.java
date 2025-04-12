package nl.mvdr.adventofcode.adventofcode2018.day20;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import nl.mvdr.adventofcode.point.Point;

/**
 * Direction.
 *
 * @author Martijn van de Rijdt
 */
enum Direction implements RoomMapExpression {
    
    /** North. */
    NORTH((points, map) -> points.stream()
            .peek(point -> map.getRooms().computeIfAbsent(point, _ -> new Room()))
            .map(Point::northNeighbour)
            .peek(point -> map.getRooms().computeIfAbsent(point, _ -> new Room()).addSouthDoor())
            .collect(Collectors.toSet())),
    
    /** East. */
    EAST((points, map) -> points.stream()
                .peek(point -> map.getRooms().computeIfAbsent(point, _ -> new Room()).addEastDoor())
                .map(Point::eastNeighbour)
                .peek(point -> map.getRooms().computeIfAbsent(point, _ -> new Room()))
                .collect(Collectors.toSet())),
    
    /** South. */
    SOUTH((points, map) -> points.stream()
            .peek(point -> map.getRooms().computeIfAbsent(point, _ -> new Room()).addSouthDoor())
            .map(Point::southNeighbour)
            .peek(point -> map.getRooms().computeIfAbsent(point, _ -> new Room()))
            .collect(Collectors.toSet())),
    
    /** West. */
    WEST((points, map) -> points.stream()
            .peek(point -> map.getRooms().computeIfAbsent(point, _ -> new Room()))
            .map(Point::westNeighbour)
            .peek(point -> map.getRooms().computeIfAbsent(point, _ -> new Room()).addEastDoor())
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
    private static Direction parse(char c) {
        return Stream.of(Direction.values())
                .filter(value -> value.getCharacterRepresentation() == c)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Unexpected character: " + c));
    }
    
    /**
     * Parses the given characters into a concatenation of directions.
     * 
     * @param string string containing only N, E, S and W
     * @return concatenation containing the directions
     */
    static Concatenation parse(String string) {
        List<Direction> directions = string.chars()
                .mapToObj(c -> parse((char)c))
                .collect(Collectors.toList());
        return new Concatenation(directions);
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
