package nl.mvdr.adventofcode.adventofcode2020.day20;

import java.util.Map;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nl.mvdr.adventofcode.solver.LongSolver;
import nl.mvdr.adventofcode.point.Point;

/**
 * Solution to the day 20 puzzle of 2020's Advent of Code:
 * <a href="https://adventofcode.com/2020/day/18">Jurassic Jigsaw</a>.
 *
 * @author Martijn van de Rijdt
 */
public class JurassicJigsawPart1 implements LongSolver {

    private static final Logger LOGGER = LoggerFactory.getLogger(JurassicJigsawPart1.class);

    /**
     * {@inheritDoc}
     * 
     * @return product of the ids of the four corners
     */
    @Override
    public long solve(Stream<String> lines) {
        Map<Point, Tile> image = Tile.reassembleImage(lines);
        
        int minX = Point.minX(image.keySet());
        int maxX = Point.maxX(image.keySet());
        int minY = Point.minY(image.keySet());
        int maxY = Point.maxY(image.keySet());
        
        Tile upperLeft = image.get(new Point(minX, minY));
        Tile upperRight = image.get(new Point(maxX, minY));
        Tile lowerLeft = image.get(new Point(minX, maxY));
        Tile lowerRight = image.get(new Point(maxX, maxY));
        
        return (long)upperLeft.id() * upperRight.id() * lowerLeft.id() * lowerRight.id();
    }

    /**
     * Main method.
     * 
     * @param args commandline arguments; these are ignored
     */
    public static void main(String[] args) {
        JurassicJigsawPart1 instance = new JurassicJigsawPart1();

        String result = instance.solve("input-day20-2020.txt");

        LOGGER.info(result);
    }
}
 