package nl.mvdr.adventofcode.adventofcode2022.day08;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.stream.Collectors;
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
                .sorted()
                .filter(this::isVisible)
                .count();
    }
    
    /**
     * Checks whether the tree at the given location is visible from any edge of the forest.
     * 
     * @param treeLocation tree location
     * @return whether the tree is visible
     */
    private boolean isVisible(Point treeLocation) {
        var result = Stream.of(Direction.values())
                .anyMatch(direction -> isVisibleFrom(treeLocation, direction));
        LOGGER.debug("Tree at {} is {}visible.", treeLocation, result ? "" : "NOT ");
        return result;
    }
    
    /**
     * Checks whether the tree at the given location is visible from the edge of the forest in the given direction.
     * 
     * @param treeLocation location of the tree
     * @param direction direction
     * @return whether the tree is visible
     */
    private boolean isVisibleFrom(Point treeLocation, Direction direction) {
        var height = Objects.requireNonNull(trees.get(treeLocation), "No tree at " + treeLocation).intValue();
        var neighbourLocation = direction.move(treeLocation);
        var neighbourHeight = Optional.ofNullable(trees.get(neighbourLocation))
                .map(OptionalInt::of)
                .orElse(OptionalInt.empty());
        boolean result;
        if (neighbourHeight.isEmpty()) {
            LOGGER.debug("Tree at {} with height {} is visible from {}: it is at the edge of the forest.",
                    treeLocation, Integer.valueOf(height), direction);
            result = true;
        } else if (height <= neighbourHeight.orElseThrow()) {
            LOGGER.debug("Tree at {} with height {} is NOT visible from {}: neighbouring tree with height {} is taller.",
                    treeLocation, Integer.valueOf(height), direction, Integer.valueOf(neighbourHeight.orElseThrow()));
            result = false;
        } else {
            result = isVisibleFrom(neighbourLocation, direction);
            LOGGER.debug("Tree at {} with height {} is {}visible from {}: neighbouring tree with height {} is {}visible.",
                    treeLocation, Integer.valueOf(height), result ? "" : "NOT ", direction, Integer.valueOf(neighbourHeight.orElseThrow()), result ? "" : "NOT ");
        }
        return result;
    }
}
