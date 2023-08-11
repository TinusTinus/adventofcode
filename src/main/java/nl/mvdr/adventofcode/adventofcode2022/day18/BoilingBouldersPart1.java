package nl.mvdr.adventofcode.adventofcode2022.day18;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nl.mvdr.adventofcode.LongSolver;
import nl.mvdr.adventofcode.point.Point3D;

/**
 * Solution to <a href="https://adventofcode.com/2022/day/18">Boiling Boulders</a>.
 *
 * @author Martijn van de Rijdt
 */
public class BoilingBouldersPart1 implements LongSolver {

    private static final Logger LOGGER = LoggerFactory.getLogger(BoilingBouldersPart1.class);

    @Override
    public long solve(Stream<String> lines) {
        var cubes = lines.map(Point3D::parse)
                .collect(Collectors.toSet());
        return cubes.stream()
                .mapToLong(cube -> countUnconnectedSides(cube, cubes))
                .sum();
    }
    
    /**
     * Returns the number of unconnected sides of the given cube.
     * 
     * @param cube cube whose sides to inspect
     * @param cubes set of all cubes
     * @return number of unconnected sides
     */
    private static long countUnconnectedSides(Point3D cube, Set<Point3D> cubes) {
        return Stream.of(Side.values())
                .map(side -> side.neighbour(cube))
                .filter(neighbour -> !cubes.contains(neighbour))
                .count();
    }
    
    /**
     * Main method.
     * 
     * @param args commandline arguments; these are ignored
     */
    public static void main(String[] args) {
        var instance = new BoilingBouldersPart1();

        var result = instance.solve("input-day18-2022.txt");

        LOGGER.info(result);
    }
}
 