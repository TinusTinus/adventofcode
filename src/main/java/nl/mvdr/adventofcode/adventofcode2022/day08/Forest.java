package nl.mvdr.adventofcode.adventofcode2022.day08;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import nl.mvdr.adventofcode.point.Direction;
import nl.mvdr.adventofcode.point.Point;

/**
 * A patch of trees.
 * 
 * @param trees trees, represented by their height
 * @author Martijn van de Rijdt
 */
record Forest(Map<Point, Integer> trees) {
    
    /**
     * Parses the puzzle input.
     * 
     * @param linesStream lines from the puzzle input
     * @return forest represented by the given puzzle input
     */
    static Forest parse(Stream<String> linesStream) {
        Map<Point, Integer> trees = new HashMap<>();
        var lines = linesStream.collect(Collectors.toList());
        for (int y = 0; y != lines.size(); y++) {
            var line = lines.get(y);
            for (int x = 0; x != line.length(); x++) {
                var height = Integer.parseInt("" + line.charAt(x));
                trees.put(new Point(x, y), Integer.valueOf(height));
            }
        }
        return new Forest(trees);
    }

    /**
     * @return number of visible trees
     */
    long countVisibleTrees() {
        return trees.keySet()
                .stream()
                .filter(this::isVisible)
                .count();
    }
    
    /**
     * Checks whether the tree at the given location is visible from any edge of the forest.
     * 
     * @param treeLocation tree location
     * @return whether the tree is visible
     */
    boolean isVisible(Point treeLocation) {
        return Stream.of(Direction.values())
                .anyMatch(direction -> isVisibleFrom(treeLocation, direction));
    }
    
    /**
     * Checks whether the tree at the given location is visible from the edge of the forest in the given direction.
     * 
     * @param treeLocation location of the tree
     * @param direction direction
     * @return whether the tree is visible
     */
    boolean isVisibleFrom(Point treeLocation, Direction direction) {
        var height = Objects.requireNonNull(trees.get(treeLocation), "No tree at " + treeLocation).intValue();
        
        var result = true;
        Point location = direction.move(treeLocation);
        while (result && trees.containsKey(location)) {
            var otherHeight = trees.get(location).intValue();
            result = otherHeight < height;
            location = direction.move(location);
        }
        return result;
    }
}
