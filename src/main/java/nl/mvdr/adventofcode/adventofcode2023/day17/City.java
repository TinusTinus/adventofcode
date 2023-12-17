package nl.mvdr.adventofcode.adventofcode2023.day17;

import java.util.List;
import java.util.Map;

import nl.mvdr.adventofcode.point.Point;

/**
 * The city through which lava needs to be moved.
 *
 * @author Martijn van de Rijdt
 */
record City(Map<Point, Block> blocks) {
    /**
     * Parses the string representation of a city.
     * 
     * @param lines lines from the puzzle input
     * @return map
     */
    static City parse(List<String> lines) {
        var blocks = Point.parse2DMap(lines, Block::parse);
        return new City(blocks);
    }
}
