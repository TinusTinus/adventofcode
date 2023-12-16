package nl.mvdr.adventofcode.adventofcode2023.day16;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nl.mvdr.adventofcode.point.Point;

/**
 * Representation of the contraption.
 *
 * @author Martijn van de Rijdt
 */
record Contraption(Map<Point, Tile> tiles) {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(Contraption.class);
    
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
    int energizedTiles() {
        return energizedTiles(BeamHead.START);
    }
    
    /**
     * Determines the number of energized tiles after the beam has passed through the contraption from the given starting point.
     * 
     * @param start the beam's starting point
     * @return number of energized tiles
     */
    private int energizedTiles(BeamHead start) {
        Set<BeamHead> visited = new HashSet<>();
        Set<BeamHead> beamHeads = Set.of(start);
        visited.add(start);
        
        while(!beamHeads.isEmpty()) {
            beamHeads = beamHeads.stream()
                    .map(beamHead -> tiles.get(beamHead.location()).passThrough(beamHead))
                    .flatMap(Set::stream)
                    .filter(beamHead -> tiles.containsKey(beamHead.location())) // Stay within bounds
                    .filter(Predicate.not(visited::contains)) // Prevent infinite loops
                    .peek(visited::add)
                    .collect(Collectors.toSet());
        }
        
        var energized = visited.stream()
                .map(BeamHead::location)
                .collect(Collectors.toSet());
        
        LOGGER.debug("{} energized tiles for starting point {}:\n{}", Integer.valueOf(energized.size()), start, Point.visualize(energized));
        
        return energized.size();
    }
    
    /**
     * @return the maximum number of energized tiles after the beam has passed through the contraption
     */
    int maxEnergizedTiles() {
        return findStartingPoints().parallelStream()
                .mapToInt(this::energizedTiles)
                .max()
                .orElse(0);
    }

    /**
     * @return possible starting points for the beam, at the edges of the contraption
     */
    private Set<BeamHead> findStartingPoints() {
        var maxX = Point.maxX(tiles.keySet());
        var maxY = Point.maxY(tiles.keySet());
        return BeamHead.findStartingPoints(maxX, maxY);
    }
}
