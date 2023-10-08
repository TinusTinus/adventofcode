package nl.mvdr.adventofcode.adventofcode2022.day22;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

import nl.mvdr.adventofcode.point.Direction;
import nl.mvdr.adventofcode.point.Point;

/**
 * Representation of the map as presented in the puzzle input.
 *
 * @param map
 *      Map of terrain, as provided in the puzzle input.
 * @param squares
 *      Six squares, making up the entire map.
 * @param edges 
 *      Indicates how to proceed when leaving a square over a certain edge.
 *      When leaving a square in a certain direction, the value for that square and direction pair in the map
 *      is a pain containing the next square and corresponding new direction.
 * @author Martijn van de Rijdt
 */
record TerrainMap(Map<Point, Terrain> map, Set<Square> squares, Map<SquareAndDirection, SquareAndDirection> edges) {
    /**
     * Parses the terrain map.
     * 
     * @param lines string representation of the terrain map
     * @return terrain map
     */
    static TerrainMap parse(List<String> lines) {
        var map = parseTerrain(lines);
        return switch(map.size()) {
            case 96     -> createMapForExampleInput(map);
            case 15_000 -> createMapForPuzzleInput(map);
            default     -> throw new IllegalArgumentException("Unexpected puzzle input.");
        };
    }

    /**
     * Creates the terrain map.
     * 
     * This method returns the map for the example input as provided in the puzzle description.
     * 
     * @param map map
     * @return terrain map
     */
    private static TerrainMap createMapForExampleInput(Map<Point, Terrain> map) {
        var size = 4;
        var square1 = new Square(new Point(9, 1), size);
        var square2 = new Square(new Point(1, 5), size);
        var square3 = new Square(new Point(5, 5), size);
        var square4 = new Square(new Point(9, 5), size);
        var square5 = new Square(new Point(9, 9), size);
        var square6 = new Square(new Point(13, 9), size);
        var squares = Set.of(square1, square2, square3, square4, square5, square6);
        
        Map<SquareAndDirection, SquareAndDirection> edges = new HashMap<>();
        // 1-2
        edges.put(new SquareAndDirection(square1, Direction.UP),    new SquareAndDirection(square2, Direction.DOWN));
        edges.put(new SquareAndDirection(square2, Direction.UP),    new SquareAndDirection(square1, Direction.DOWN));
        // 1-3
        edges.put(new SquareAndDirection(square1, Direction.LEFT),  new SquareAndDirection(square3, Direction.DOWN));
        edges.put(new SquareAndDirection(square3, Direction.UP),    new SquareAndDirection(square1, Direction.RIGHT));
        // 1-6
        edges.put(new SquareAndDirection(square1, Direction.RIGHT), new SquareAndDirection(square6, Direction.LEFT));
        edges.put(new SquareAndDirection(square6, Direction.RIGHT), new SquareAndDirection(square1, Direction.LEFT));
        // 2-5
        edges.put(new SquareAndDirection(square2, Direction.DOWN),  new SquareAndDirection(square5, Direction.UP));
        edges.put(new SquareAndDirection(square5, Direction.DOWN),  new SquareAndDirection(square2, Direction.UP));
        // 2-6
        edges.put(new SquareAndDirection(square2, Direction.LEFT),  new SquareAndDirection(square6, Direction.UP));
        edges.put(new SquareAndDirection(square6, Direction.DOWN),  new SquareAndDirection(square2, Direction.RIGHT));
        // 3-5
        edges.put(new SquareAndDirection(square3, Direction.DOWN),  new SquareAndDirection(square5, Direction.RIGHT));
        edges.put(new SquareAndDirection(square5, Direction.LEFT),  new SquareAndDirection(square3, Direction.UP));
        // 4-6
        edges.put(new SquareAndDirection(square4, Direction.RIGHT), new SquareAndDirection(square6, Direction.DOWN));
        edges.put(new SquareAndDirection(square6, Direction.UP),    new SquareAndDirection(square4, Direction.LEFT));
        
        return new TerrainMap(map, squares, edges);
    }
    

    /**
     * Creates the terrain map.
     * 
     * This method returns the map for the actual puzzle input.
     * 
     * Note that this is specific for particular puzzle input.
     * This solution is not applicable for the general case!
     * 
     * @param map map
     * @return terrain map
     */
    private static TerrainMap createMapForPuzzleInput(Map<Point, Terrain> map) {
        var size = 50;
        var square1 = new Square(new Point(51, 1), size);
        var square2 = new Square(new Point(101, 1), size);
        var square3 = new Square(new Point(51, 51), size);
        var square4 = new Square(new Point(1, 101), size);
        var square5 = new Square(new Point(51, 101), size);
        var square6 = new Square(new Point(1, 151), size);
        var squares = Set.of(square1, square2, square3, square4, square5, square6);
        
        Map<SquareAndDirection, SquareAndDirection> edges = Map.of(); // TODO
        
        return new TerrainMap(map, squares, edges);
    }
    
    /**
     * Parses the terrain map.
     * 
     * @param lines string representation of the terrain map
     * @return terrain map
     */
    private static Map<Point, Terrain> parseTerrain(List<String> lines) {
        Map<Point, Terrain> map = new HashMap<>();
        for (var y = 1; y != lines.size() + 1; y++) {
            String line = lines.get(y - 1);
            for (var x = 1; x != line.length() + 1; x++) {
                var terrain = Terrain.parse(line.charAt(x - 1));
                if (terrain.isPresent()) {
                    map.put(new Point(x, y), terrain.orElseThrow());
                }
            }
        }
        return map;
    }
    
    /**
     * @return starting position on this map
     */
    Position startingPosition() {
        // You begin the path in the leftmost open tile of the top row of tiles.
        var startingLocation = map.keySet()
                .stream()
                .filter(point -> point.y() == 1) // top row
                .filter(point -> map.get(point) == Terrain.OPEN_TILE) // open tile
                .min(Comparator.comparing(Point::x)) // leftmost
                .orElseThrow();
        // Initially, you are facing to the right (from the perspective of how the map is drawn).
        return new Position(startingLocation, Direction.RIGHT);
    }
    
    /**
     * Wraps around the map.
     * 
     * @param startingPosition starting position, at the edge of the map
     * @return the next position when moving 1 position in the given direction
     */
    Position wrapAround(Position startingPosition) {
        var startingLocation = startingPosition.location();
        var startingFacing = startingPosition.facing();
        var startingSquare = squares.stream()
                .filter(square -> square.contains(startingLocation))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Position not found in any square: " + startingPosition));
        
        var newSquareAndDirection = edges.get(new SquareAndDirection(startingSquare, startingFacing));
        Objects.requireNonNull(newSquareAndDirection, "Edge not found for wrapping around from position " + startingPosition);
        
        var newSquare = newSquareAndDirection.square();
        var newFacing = newSquareAndDirection.direction();
        
        var newRelativeLocation = startingSquare.wrapAround(startingPosition, newFacing);
        var newLocation = newSquare.toAbsolute(newRelativeLocation);
        return new Position(newLocation, newFacing);
    }
}
