package nl.mvdr.adventofcode.adventofcode2023.day16;

import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

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
        // TODO remove!
        findStartingPoints().parallelStream()
                .max(Comparator.comparing(this::energizedTiles))
                .ifPresent(System.out::println); // BeamHead[location=3,0, direction=v] is found as the best starting point for the example, why is the answer off by one?
        
        return findStartingPoints().parallelStream()
                .mapToLong(this::energizedTiles)
                .max()
                .orElse(0L);
    }

    /**
     * @return possible startings points for the beam, at the edges of the contraption
     */
    private Set<BeamHead> findStartingPoints() {
        var maxX = Point.maxX(tiles.keySet());
        var maxY = Point.maxY(tiles.keySet());
        return BeamHead.findStartingPoints(maxX, maxY);
    }
}
