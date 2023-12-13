package nl.mvdr.adventofcode.adventofcode2023.day13;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import nl.mvdr.adventofcode.point.Point;

/**
 * A pattern of ash and rocks.
 *
 * @author Martijn van de Rijdt
 */
record Pattern(Map<Point, Terrain> map, int width, int height) {
    
    /**
     * Parses the string representation of a list of patterns.
     * 
     * @param lines puzzle input
     * @return patterns
     */
    static List<Pattern> parse(List<String> lines) {
        var remainingLines = lines;
        List<Pattern> result = new ArrayList<>();
        while(!remainingLines.isEmpty()) {
            var emptyLineIndex = remainingLines.indexOf("");
            List<String> patternLines;
            if (emptyLineIndex < 0) {
                patternLines = remainingLines;
                remainingLines = List.of();
            } else {
                patternLines = remainingLines.subList(0, emptyLineIndex);
                remainingLines = remainingLines.subList(emptyLineIndex + 1, remainingLines.size());
            }
            result.add(parsePattern(patternLines));
        }
        return result;
    }
    
    /**
     * Parses a single pattern.
     * 
     * @param lines portion of the puzzle input representing a single pattern
     * @return pattern
     */
    private static Pattern parsePattern(List<String> lines) {
        var height = lines.size();
        var width = lines.getFirst().length();
        
        Map<Point, Terrain> map = new HashMap<>();
        for (var y = 0; y != height; y++) {
            var line = lines.get(y);
            if (line.length() != width) {
                throw new IllegalArgumentException("Input must be rectangular: all lines must have the same length ("
                        + width + "). But the length of line " + y + " is: " + line.length());
            }
            for (var x = 0; x != width; x++) {
                var terrain = Terrain.of(line.charAt(x));
                var point = new Point(x, y);
                map.put(point, terrain);
            }
        }
        
        return new Pattern(map, width, height);
    }
}
