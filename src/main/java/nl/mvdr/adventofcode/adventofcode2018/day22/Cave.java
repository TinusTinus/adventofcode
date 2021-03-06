package nl.mvdr.adventofcode.adventofcode2018.day22;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nl.mvdr.adventofcode.point.Point;

/**
 * Representation of the cave.
 * 
 * @author Martijn van de Rijdt
 */
class Cave {
    /** Mouth of the cave. */
    static final Point MOUTH = Point.ORIGIN;
    
    /** Logger. */
    private static final Logger LOGGER = LoggerFactory.getLogger(Cave.class);
    
    /** Depth of the cave. */
    private final int depth;
    /** Location of the target (Santa's friend). */
    private final Point target;
    
    /** Cached region valu1es. */
    private final Map<Point, Region> regions;
    
    /**
     * Creates a new cave based on the puzzle input.
     * 
     * @param linesStream contents of the text file containing the puzzle input
     * @return cave
     * @throws IOException in case the input file could not be read
     */
    static Cave parse(Stream<String> linesStream) {
        List<String> lines = linesStream.collect(Collectors.toList());
        int depth = Integer.parseInt(lines.get(0).substring("depth: ".length()));
        Point target = Point.parse(lines.get(1).substring("target: ".length()));
        
        return new Cave(depth, target);
    }
    
    /**
     * Constructor.
     * 
     * @param depth depth of the cave
     * @param target coordinates of the target
     */
    private Cave(int depth, Point target) {
        super();
        this.depth = depth;
        this.target = target;
        this.regions = new HashMap<>();
    }
    
    Point getTarget() {
        return target;
    }
    
    /**
     * Gets the region at the given coordinates.
     * 
     * @param point location of the region
     * @return region
     */
    Region getRegionAt(Point point) {
        Region result = regions.get(point);
        
        if (result == null) {
            result = computeRegion(point);
            regions.put(point, result);
        }
        
        return result;
    }
    
    /**
     * Creates a new region for the given point.
     * 
     * @param point location of the region
     * @return region
     */
    private Region computeRegion(Point point) {
        int geologicIndex;
        
        if (point.x() < 0) {
            throw new IllegalArgumentException("x coordinate was negative: " + point.x());
        } else if (point.y() < 0) {
            throw new IllegalArgumentException("y coordinate was negative: " + point.y());
        } else if (point.equals(MOUTH)) {
            geologicIndex = 0;
        } else if (point.equals(target)) {
            geologicIndex = 0;
        } else if (point.y() == 0) {
            geologicIndex = point.x() * 16_807;
        } else if (point.x() == 0) {
            geologicIndex = point.y() * 48_271;
        } else {
            // Lookup recursively
            geologicIndex = getRegionAt(point.aboveNeighbour()).getErosionLevel()
                    * getRegionAt(point.leftNeighbour()).getErosionLevel();
        }
        
        Region region = new Region(geologicIndex, this.depth);
        LOGGER.debug("new Region {}: {}", point, region);
        return region;
    }
    
    /** @return the total risk level for the smallest rectangle that includes 0,0 and the target's coordinates */
    int getTotalRiskLevel() {
        return IntStream.rangeClosed(0, target.x())
                .boxed()
                .flatMap(x -> IntStream.rangeClosed(0, target.y()).mapToObj(y -> new Point(x.intValue(), y)))
                .map(this::getRegionAt)
                .map(Region::getType)
                .mapToInt(Type::getRiskLevel)
                .sum();
    }
}
