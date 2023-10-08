package nl.mvdr.adventofcode.adventofcode2022.day22;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
        
        Set<Square> squares = Set.of(); // TODO
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
}
