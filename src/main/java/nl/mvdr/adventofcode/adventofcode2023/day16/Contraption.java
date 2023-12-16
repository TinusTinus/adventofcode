package nl.mvdr.adventofcode.adventofcode2023.day16;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import nl.mvdr.adventofcode.point.Direction;
import nl.mvdr.adventofcode.point.Point;

/**
 * Representation of the contraption.
 *
 * @author Martijn van de Rijdt
 */
record Contraption(Map<Point, Tile> tiles) {
    /**
     * Parses a string representation of a contraption.
     * 
     * @param lines puzzle input
     * @return contraption
     */
    static Contraption parse(List<String> lines) {
        Map<Point, Tile> tiles = new HashMap<>();
        for (var y = 0; y != lines.size(); y++) {
            var line = lines.get(y);
            for (var x = 0; x != line.length(); x++) {
                var location = new Point(x, y);
                var tile = Tile.of(line.charAt(x));
                tiles.put(location, tile);
            }
        }
        return new Contraption(tiles);
    }
    
    /**
     * Determines the number of energized tiles after the beam has passed through the contraption,
     * from the top-left starting point, moving to the right.
     * 
     * @return number of energized tiles
     */
    long energizedTiles() {
        return energizedTiles(BeamHead.START);
    }
    
    /**
     * Determines the number of energized tiles after the beam has passed through the contraption from the given starting point.
     * 
     * @param start the beam's starting point
     * @return number of energized tiles
     */
    private long energizedTiles(BeamHead start) {
        Set<BeamHead> visited = new HashSet<>();
        Set<BeamHead> beamHeads = Set.of(start);
        visited.add(BeamHead.START);
        
        while(!beamHeads.isEmpty()) {
            beamHeads = beamHeads.stream()
                    .map(beamHead -> tiles.get(beamHead.location()).passThrough(beamHead))
                    .flatMap(Set::stream)
                    .filter(beamHead -> tiles.containsKey(beamHead.location())) // Stay within bounds
                    .filter(Predicate.not(visited::contains)) // Prevent infinite loops
                    .peek(visited::add)
                    .collect(Collectors.toSet());
        }
        
        return visited.stream()
                .map(BeamHead::location)
                .distinct()
                .count();
    }
    
    /**
     * @return the maximum number of energized tiles after the beam has passed through the contraption
     */
    long maxEnergizedTiles() {
        var width = Point.maxX(tiles.keySet());
        var height = Point.maxY(tiles.keySet());
        Set<BeamHead> startingPoints = new HashSet<>();
        IntStream.range(0, width)
                .peek(x -> startingPoints.add(new BeamHead(x, 0, Direction.DOWN))) // Starting from the top
                .forEach(x -> startingPoints.add(new BeamHead(x, height - 1, Direction.UP))); // Starting from the bottom
        IntStream.range(0, height)
                .peek(y -> startingPoints.add(new BeamHead(0, y, Direction.RIGHT))) // Starting from the left
                .forEach(y -> startingPoints.add(new BeamHead(width - 1, y, Direction.LEFT))); // Starting from the right
        
        return startingPoints.stream()
                .mapToLong(this::energizedTiles)
                .max()
                .orElse(0L);
    }
}
