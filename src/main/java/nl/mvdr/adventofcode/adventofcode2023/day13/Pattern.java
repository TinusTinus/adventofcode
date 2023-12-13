package nl.mvdr.adventofcode.adventofcode2023.day13;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.OptionalInt;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

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
    
    /**
     * @return number of columns to the left of the vertical mirror in this pattern
     */
    private OptionalInt findVerticalMirror() {
        return findVerticalMirrors().findAny();
    }
    
    /**
     * @return indexes of any vertical mirrors in the input
     */
    private IntStream findVerticalMirrors() {
        return IntStream.range(1, width)
                .filter(this::mirrorsVerticallyAt);
    }
    
    /**
     * Checks whether there is a vertical mirror at the given index.
     * 
     * That is, whether all columns left of the given index are a mirror image of all columns right of the given index.
     * 
     * @param index index to inspect
     * @return whether there is a mirror at the given index
     */
    private boolean mirrorsVerticallyAt(int index) {
        return IntStream.range(0, width - index)
                .filter(offset -> 0 <= index - offset - 1)
                .allMatch(offset -> getColumn(index + offset).equals(getColumn(index - offset - 1)));
    }

    /**
     * Gets the column with the given x coordinate.
     * 
     * @param x coordinate
     * @return column of values
     */
    private List<Terrain> getColumn(int x) {
        return map.entrySet()
                .stream()
                .filter(entry -> entry.getKey().x() == x)
                .sorted(Comparator.comparing(Entry::getKey))
                .map(Entry::getValue)
                .toList();
    }
    
    /**
     * @return number of rows above the horizontal mirror in this pattern
     */
    private OptionalInt findHorizontalMirror() {
        return transpose().findVerticalMirror();
    }
    
    /**
     * @return transposed version of this pattern
     */
    private Pattern transpose() {
        var transposedMap = map.entrySet()
                .stream()
                .collect(Collectors.toMap(entry -> entry.getKey().transpose(), Entry::getValue));
        return new Pattern(transposedMap, height, width);
    }
    
    /**
     * @return summary of this pattern's notes; that is, info about the mirror's location
     */
    int summarize() {
        return findVerticalMirror()
                .orElseGet(() -> 100 * findHorizontalMirror()
                        .orElseThrow(() -> new IllegalStateException("No mirror found in " + this)));
    }
    
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder("Pattern:");
        for (var y = 0; y != height; y++) {
            builder.append("\n");
            for (var x = 0; x != width; x++) {
                builder.append(map.get(new Point(x, y)));
            }
        }
        return builder.toString();
    }
}
