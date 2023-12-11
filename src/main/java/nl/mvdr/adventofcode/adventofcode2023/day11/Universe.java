package nl.mvdr.adventofcode.adventofcode2023.day11;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import nl.mvdr.adventofcode.point.Point;

/**
 * Representation of a universe.
 *
 * @author Martijn van de Rijdt
 */
record Universe(Set<Point> galaxies, int width, int height) {
    /**
     * Parses the textual representation of a universe.
     * 
     * @param lines lines from the puzzle input 
     * @return (unexpanded!) universe
     */
    static Universe parse(List<String> lines) {
        var width = lines.getFirst().length(); // input is expected to be rectangular: all lines are of equal length
        var height = lines.size();
        
        Set<Point> galaxies = new HashSet<>();
        for (var y = 0; y != height; y++) {
            var line = lines.get(y);
            for (var x = 0; x != width; x++) {
                var character = line.charAt(x);
                if (character == '#') {
                    galaxies.add(new Point(x, y));
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
     * @return a copy of this universe, where any rows or columns that contain no galaxies have been doubled
     */
    Universe expand() {
        return expandHorizontally().expandVertically();
    }
    
    /**
     * Expands the universe horizontally.
     * 
     * @return a copy of this universe, where any columns that contain no galaxies have been doubled
     */
    private Universe expandHorizontally() {
        var emptyColumns = IntStream.range(0, width)
                .filter(x -> !galaxies.stream().anyMatch(galaxy -> galaxy.x() == x))
                .boxed()
                .toList();
        
        var newWidth = width + emptyColumns.size();
        
        var newGalaxies = galaxies.stream()
                .map(galaxy -> new Point(galaxy.x() + (int)emptyColumns.stream().filter(x -> x.intValue() < galaxy.x()).count(), galaxy.y()))
                .collect(Collectors.toSet());
        
        return new Universe(newGalaxies, newWidth, height);
    }
    
    /**
     * Expands the universe vertically.
     * 
     * @return a copy of this universe, where any rows that contain no galaxies have been doubled
     */
    private Universe expandVertically() {
        var emptyRows = IntStream.range(0, height)
                .filter(y -> !galaxies.stream().anyMatch(galaxy -> galaxy.y() == y))
                .boxed()
                .toList();
        
        var newHeight = height + emptyRows.size();
        
        var newGalaxies = galaxies.stream()
                .map(galaxy -> new Point(galaxy.x(), galaxy.y() + (int)emptyRows.stream().filter(y -> y.intValue() < galaxy.y()).count()))
                .collect(Collectors.toSet());
        
        return new Universe(newGalaxies, width, newHeight);
    }
}
