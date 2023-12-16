package nl.mvdr.adventofcode.adventofcode2023.day16;

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
     * @return the number of energized tiles after the beam has passed through the contraption
     */
    long energizedTiles() {
        Set<BeamHead> visited = new HashSet<>();
        Set<BeamHead> beamHeads = Set.of(BeamHead.START);
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
}
