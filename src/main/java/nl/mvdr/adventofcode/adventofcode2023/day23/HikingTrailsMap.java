package nl.mvdr.adventofcode.adventofcode2023.day23;

import java.util.List;
import java.util.Map;

import nl.mvdr.adventofcode.point.Point;

/**
 * Map of hiking trails.
 *
 * @author Martijn van de Rijdt
 */
public record HikingTrailsMap(Map<Point, Terrain> map) {

    /**
     * Parses a hiking trails map.
     * 
     * @param lines puzzle input
     * @return map
     */
    static HikingTrailsMap parse(List<String> lines) {
        return new HikingTrailsMap(Point.parse2DMap(lines, Terrain::of));
    }
}
