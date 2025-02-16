package nl.mvdr.adventofcode.adventofcode2023.day13;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.OptionalInt;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nl.mvdr.adventofcode.point.Point;

/**
 * A pattern of ash and rocks.
 *
 * @author Martijn van de Rijdt
 */
record Pattern(Map<Point, Terrain> map, int width, int height) {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(Pattern.class);
    
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
        var map = Point.parse2DMap(lines, Terrain::of);
        var height = lines.size();
        var width = lines.getFirst().length();
        return new Pattern(map, width, height);
    }
    
    /**
     * Finds the vertical mirror.
     * 
     * @param smudges number of smudges on the mirror
     * @return number of columns to the left of the vertical mirror in this pattern
     */
    private OptionalInt findVerticalMirror(int smudges) {
        return findVerticalMirrors(smudges).findAny();
    }
    
    /**
     * Finds any vertical mirrors.
     * 
     * @param smudges number of smudges on the mirror
     * @return indexes of any vertical mirrors in the input
     */
    private IntStream findVerticalMirrors(int smudges) {
        return IntStream.range(1, width)
                .filter(x -> mirrorsVerticallyAt(x, smudges));
    }
    
    /**
     * Checks whether there is a vertical mirror at the given index.
     * 
     * That is, whether all columns left of the given index are a mirror image of all columns right of the given index.
     * 
     * @param index index to inspect
     * @param smudges number of smudges on the mirror
     * @return whether there is a mirror at the given index
     */
    private boolean mirrorsVerticallyAt(int index, int smudges) {
        var differences = IntStream.range(0, width - index)
                .filter(offset -> 0 <= index - offset - 1)
                .map(offset -> countDifferences(index + offset, index - offset - 1))
                .sum();
        return differences == smudges;
    }

    /**
     * Counts the number of differing values in the columns with the given indexes.
     * 
     * @param column0 x coordinate of the first column
     * @param column1 x coordinate of the second column
     * @return the number of differing values between the two columns
     */
    private int countDifferences(int column0, int column1) {
        return (int)IntStream.range(0, height)
                .filter(y -> map.get(new Point(column0, y)) != map.get(new Point(column1, y)))
                .count();
    }
    
    /**
     * Finds the horizontal mirror.
     * 
     * @param smudges number of smudges on the mirror
     * @return number of rows above the horizontal mirror in this pattern
     */
    private OptionalInt findHorizontalMirror(int smudges) {
        return transpose().findVerticalMirror(smudges);
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
     * Summarizes this pattern.
     * 
     * @param smudges number of smudges on the mirror
     * @return summary of this pattern's notes; that is, info about the mirror's location
     */
    int summarize(int smudges) {
        var result = findVerticalMirror(smudges)
                .orElseGet(() -> 100 * findHorizontalMirror(smudges)
                        .orElseThrow(() -> new IllegalStateException("No mirror found in " + this)));
        LOGGER.debug("Summary: {} for {}", Integer.valueOf(result), this);
        return result;
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
