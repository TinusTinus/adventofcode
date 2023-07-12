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
class RegolithReservoir implements IntSolver {

    private static final Logger LOGGER = LoggerFactory.getLogger(RegolithReservoir.class);

    private final boolean floor;

    /**
     * Constructor.
     * 
     * @param floor whether there is a floor
     */
    RegolithReservoir(boolean floor) {
        super();
        this.floor = floor;
    }
    
    @Override
    public int solve(Stream<String> lines) {
        var rock = Path.parse(lines)
                .stream()
                .flatMap(path -> path.allPoints().stream())
                .collect(Collectors.toSet());
        var spring = new Point(500, 0);
        var verticalSlice = new VerticalSlice(spring, rock, false, floor).tickUntilDone();
        LOGGER.info(verticalSlice.toString());
        return verticalSlice.countSettled();
    }
}
 