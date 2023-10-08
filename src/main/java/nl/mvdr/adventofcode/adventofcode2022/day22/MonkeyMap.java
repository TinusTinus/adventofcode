package nl.mvdr.adventofcode.adventofcode2022.day22;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nl.mvdr.adventofcode.IntSolver;
import nl.mvdr.adventofcode.point.Direction;
import nl.mvdr.adventofcode.point.Point;

/**
 * Solution to <a href="https://adventofcode.com/2022/day/22">Monkey Map</a>.
 *
 * @author Martijn van de Rijdt
 */
class MonkeyMap implements IntSolver {

    private static final Logger LOGGER = LoggerFactory.getLogger(MonkeyMap.class);

    private final WrapAroundStrategy strategy;
    
    /**
     * Constructor.
     * 
     * @param strategy rules for wrapping around the edges of the map
     */
    MonkeyMap(WrapAroundStrategy strategy) {
        this.strategy = strategy;
    }
    
    @Override
    public int solve(Stream<String> linesStream) {
        // Parse the puzzle input
        List<String> lines = linesStream.toList();
        Map<Point, Terrain> map = parseTerrain(lines);
        var path = Path.parse(lines.get(lines.size() - 1), strategy);
        
        // You begin the path in the leftmost open tile of the top row of tiles.
        var startingLocation = map.keySet()
                .stream()
                .filter(point -> point.y() == 1) // top row
                .filter(point -> map.get(point) == Terrain.OPEN_TILE) // open tile
                .min(Comparator.comparing(Point::x)) // leftmost
                .orElseThrow();
        // Initially, you are facing to the right (from the perspective of how the map is drawn).
        var startingPosition = new Position(startingLocation, Direction.RIGHT);
        LOGGER.debug("Starting position: {}", startingPosition);
        
        Position finalPosition = path.execute(startingPosition, map);
        
        return finalPosition.computePassword();
    }

    /**
     * Parses the terrain map.
     * 
     * @param lines puzzle input
     * @return terrain map
     */
    private static Map<Point, Terrain> parseTerrain(List<String> lines) {
        Map<Point, Terrain> map = new HashMap<>();
        for (var y = 1; y != lines.size(); y++) {
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
}
 