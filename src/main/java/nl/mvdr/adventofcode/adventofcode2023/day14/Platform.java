package nl.mvdr.adventofcode.adventofcode2023.day14;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import nl.mvdr.adventofcode.point.Point;

/**
 * Representation of the large metal platform holding a bunch of rocks.
 *
 * @author Martijn van de Rijdt
 */
record Platform(Set<Point> roundedRocks, Set<Point> cubeRocks, int width, int height) {
    
    /**
     * Parses a string representation of a platform.
     * 
     * @param lines puzzle input
     * @return platform
     */
    static Platform parse(List<String> lines) {
        var height = lines.size();
        var width = lines.getFirst().length();
        
        Set<Point> roundedRocks = new HashSet<>();
        Set<Point> cubeRocks = new HashSet<>();
        
        for (var y = 0; y != height; y++) {
            var line = lines.get(y);
            for (var x = 0; x != width; x++) {
                var c = line.charAt(x);
                if (c == 'O') {
                    roundedRocks.add(new Point(x, y));
                } else if (c == '#') {
                    cubeRocks.add(new Point(x, y));
                } else if (c != '.') {
                    throw new IllegalArgumentException("Unexpected character found in input: " + c);
                }
            }
        }
        
        return new Platform(roundedRocks, cubeRocks, width, height);
    }
}
