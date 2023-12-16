package nl.mvdr.adventofcode.adventofcode2022.day08;

import java.util.Map;
import java.util.Objects;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nl.mvdr.adventofcode.point.Direction;
import nl.mvdr.adventofcode.point.Point;

/**
 * A patch of trees.
 * 
 * @param trees trees, represented by their height
 * @author Martijn van de Rijdt
 */
record Forest(Map<Point, Integer> trees) {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(Forest.class);
    
    /**
     * Parses the puzzle input.
     * 
     * @param linesStream lines from the puzzle input
     * @return forest represented by the given puzzle input
     */
    static Forest parse(Stream<String> linesStream) {
        Map<Point, Integer> trees = Point.parse2DMap(linesStream.toList(),
                character -> Integer.valueOf("" + character));
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
        return Stream.of(Direction.UP, Direction.DOWN, Direction.LEFT, Direction.RIGHT)
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

    /**
     * @return optimal scenic score for this forest
     */
    int optimalScenicScore() {
        return trees.keySet()
                .stream()
                .mapToInt(this::scenicScore)
                .max()
                .orElseThrow();
    }
    
    /**
     * Computes the scenic score for a specific tree.
     * 
     * @param location location of the tree
     * @return scenic score for this tree
     */
    int scenicScore(Point location) {
        return Stream.of(Direction.UP, Direction.DOWN, Direction.LEFT, Direction.RIGHT)
                .mapToInt(direction -> viewingDistance(location, direction))
                .reduce(1, (i, j) -> i * j);
    }
    
    /**
     * Calculates the viewing distance from the given tree in the given direction.
     * 
     * To measure the viewing distance from a given tree, look up, down, left, and
     * right from that tree; stop if you reach an edge or at the first tree that is
     * the same height or taller than the tree under consideration. (If a tree is
     * right on the edge, at least one of its viewing distances will be zero.)
     * 
     * @param treeLocation location of the tree
     * @param direction    viewing direction
     * @return viewing distance
     */
    private int viewingDistance(Point treeLocation, Direction direction) {
        var height = Objects.requireNonNull(trees.get(treeLocation), "No tree at " + treeLocation).intValue();
        
        var result = 0;
        var location = direction.move(treeLocation);
        var locationVisible = true;
        while (locationVisible && trees.containsKey(location)) {
            result++;
            locationVisible = trees.get(location).intValue() < height;
            location = direction.move(location);
        }
        
        LOGGER.debug("Viewing distance from {} (height: {}) in the direction {}: {}",
                treeLocation, Integer.valueOf(height), direction, Integer.valueOf(result));
        return result;
    }
}
