package nl.mvdr.adventofcode.adventofcode2022.day14;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nl.mvdr.adventofcode.IntSolver;
import nl.mvdr.adventofcode.point.Point;
import nl.mvdr.adventofcode.verticalslice.VerticalSlice;

/**
 * Solution to <a href="https://adventofcode.com/2022/day/14">Regolith Reservoir</a>.
 *
 * @author Martijn van de Rijdt
 */
public class RegolithReservoirPart1 implements IntSolver {

    private static final Logger LOGGER = LoggerFactory.getLogger(RegolithReservoirPart1.class);
    
    @Override
    public int solve(Stream<String> lines) {
        var rock = Path.parse(lines)
                .stream()
                .flatMap(path -> path.allPoints().stream())
                .collect(Collectors.toSet());
        var spring = new Point(500, 0);
        var verticalSlice = new VerticalSlice(spring, rock, true);
        return verticalSlice.tickUntilDone().countSettled();
    }

    /**
     * Main method.
     * 
     * @param args commandline arguments; these are ignored
     */
    public static void main(String[] args) {
        var instance = new RegolithReservoirPart1();

        var result = instance.solve("input-day14-2022.txt");

        LOGGER.info(result);
    }
}
 