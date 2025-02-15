package nl.mvdr.adventofcode.adventofcode2023.day11;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Representation of a universe.
 *
 * @author Martijn van de Rijdt
 */
record Universe(Set<Galaxy> galaxies, int width, int height) {
    /**
     * Parses the textual representation of a universe.
     * 
     * @param lines lines from the puzzle input 
     * @return (unexpanded!) universe
     */
    static Universe parse(List<String> lines) {
        var width = lines.getFirst().length(); // input is expected to be rectangular: all lines are of equal length
        var height = lines.size();
        
        Set<Galaxy> galaxies = new HashSet<>();
        for (var y = 0; y != height; y++) {
            var line = lines.get(y);
            for (var x = 0; x != width; x++) {
                var character = line.charAt(x);
                if (character == '#') {
                    galaxies.add(new Galaxy(x, y));
                } else if (character != '.') {
                    throw new IllegalArgumentException(
                            "Unexpected input character '" + character + "' in line " + line);
                }
            }
        }
        
        return new Universe(galaxies, width, height);
    }
    
    /**
     * Expands the universe.
     * 
     * @param expansionFactor the factor by which the universe should be expanded;
     *       that is, the number of rows / columns by which to replace each empty row and column
     * @return a copy of this universe, where any rows or columns that contain no galaxies have been expanded
     */
    Universe expand(int expansionFactor) {
        return expandHorizontally(expansionFactor).expandVertically(expansionFactor);
    }
    
    /**
     * Expands the universe horizontally.
     * 
     * @param expansionFactor the amount by which the universe should be expanded;
     *       that is, the number of columns by which to replace each empty colum
     * @return a copy of this universe, where any columns that contain no galaxies have been expanded
     */
    private Universe expandHorizontally(int expansionFactor) {
        var emptyColumns = IntStream.range(0, width)
                .filter(x -> !galaxies.stream().anyMatch(galaxy -> galaxy.x() == x))
                .boxed()
                .toList();
        
        var newWidth = width + emptyColumns.size();
        
        var newGalaxies = galaxies.stream()
                .map(galaxy -> new Galaxy(galaxy.x() + (expansionFactor - 1) * emptyColumns.stream().filter(x -> x.intValue() < galaxy.x()).count(), galaxy.y()))
                .collect(Collectors.toSet());
        
        return new Universe(newGalaxies, newWidth, height);
    }
    
    /**
     * Expands the universe vertically.
     * 
     * @param expansionFactor the amount by which the universe should be expanded;
     *       that is, the number of rows by which to replace each empty row
     * @return a copy of this universe, where any rows that contain no galaxies have been expanded
     */
    private Universe expandVertically(int expansionFactor) {
        var emptyRows = IntStream.range(0, height)
                .filter(y -> !galaxies.stream().anyMatch(galaxy -> galaxy.y() == y))
                .boxed()
                .toList();
        
        var newHeight = height + emptyRows.size();
        
        var newGalaxies = galaxies.stream()
                .map(galaxy -> new Galaxy(galaxy.x(), galaxy.y() + (expansionFactor - 1) * emptyRows.stream().filter(y -> y.intValue() < galaxy.y()).count()))
                .collect(Collectors.toSet());
        
        return new Universe(newGalaxies, width, newHeight);
    }
    
    /**
     * @return sum of the shortest path between each pair of galaxies
     */
    long sumShortestPaths() {
        var pathLengths = generatePairs().mapToLong(GalaxyPair::computeDistance)
                .sum();
        // every path was counted twice
        return pathLengths / 2L;
    }
    
    /**
     * Generates a set of all pairs of galaxies.
     * 
     * Note that every pair will occur twice:
     * once as (g0, g1) and once as (g1, g0).
     * 
     * @return all pairs of galaxies
     */
    private Stream<GalaxyPair> generatePairs() {
        return galaxies.stream()
                .flatMap(galaxy0 -> galaxies.stream()
                        .map(galaxy1 -> new GalaxyPair(galaxy0, galaxy1)));
                
    }
}
