package nl.mvdr.adventofcode.adventofcode2020.day03;

import java.util.List;
import java.util.Set;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import nl.mvdr.adventofcode.point.Point;

/**
 * Map of an area containing trees.
 *
 * @author Martijn van de Rijdt
 */
class Map {
    private final Set<Point> trees;
    
    private final int width;
    
    private final int height;
    
    
    /**
     * Parses the puzzle input into a map.
     * 
     * @param lines lines from the puzzle input
     * @return map represented by the given input
     */
    static Map parse(Stream<String> linesStream) {
        List<String> lines = linesStream.filter(Predicate.not(String::isEmpty))
                .collect(Collectors.toList());
        
        int height = lines.size();
        int width = lines.get(0).length();
        
        Set<Point> trees = IntStream.range(0, height)
                .mapToObj(y -> IntStream.range(0, width).mapToObj(x -> new Point(x, y)))
                .flatMap(Function.identity())
                .filter(point -> lines.get(point.y()).charAt(point.x()) == '#')
                .collect(Collectors.toSet());
        
        return new Map(width, height, trees);
    }
    
    /**
     * Constructor.
     * 
     * @param width width of (a single occurrence of) the map
     * @param height height of the map
     * @param trees locations of the trees in the map
     */
    private Map(int width, int height, Set<Point> trees) {
        super();
        this.width = width;
        this.height = height;
        this.trees = trees;
    }
    
    /**
     * Counts the number of trees encountered while traveling at the given slope.
     * 
     * @param slope slope
     * @return number of trees encountered
     */
    long countTrees(Point slope) {
        return IntStream.range(0, height / slope.y() + 1)
                .mapToObj(i -> new Point((i * slope.x()) % width, i * slope.y()))
                .filter(trees::contains)
                .count();
    }
    
    /** @return height of the map */
    int getHeight() {
        return height;
    }
}
