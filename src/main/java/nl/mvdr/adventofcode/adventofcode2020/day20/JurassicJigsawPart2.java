package nl.mvdr.adventofcode.adventofcode2020.day20;

import java.util.Map;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nl.mvdr.adventofcode.IntSolver;
import nl.mvdr.adventofcode.point.Point;

/**
 * Solution to the day 20 puzzle of 2020's Advent of Code:
 * <a href="https://adventofcode.com/2020/day/18">Jurassic Jigsaw</a>.
 *
 * @author Martijn van de Rijdt
 */
public class JurassicJigsawPart2 implements IntSolver {

    private static final Logger LOGGER = LoggerFactory.getLogger(JurassicJigsawPart2.class);

    /**
     * {@inheritDoc}
     * 
     * @return water roughness
     */
    @Override
    public int solve(Stream<String> lines) {
        Map<Point, Tile> image = Tile.reassembleImage(lines);
        LOGGER.debug("Image: {}", image);
        Tile imageTile = Tile.asTile(image);
        LOGGER.debug("Image tile: {}", imageTile);
        return imageTile.waterRoughness();
    }

    /**
     * Main method.
     * 
     * @param args commandline arguments; these are ignored
     */
    public static void main(String[] args) {
        JurassicJigsawPart2 instance = new JurassicJigsawPart2();

        String result = instance.solve("input-day20-2020.txt");

        LOGGER.info(result);
    }
}
 