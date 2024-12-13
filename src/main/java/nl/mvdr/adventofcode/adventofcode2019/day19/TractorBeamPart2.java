package nl.mvdr.adventofcode.adventofcode2019.day19;

import java.util.Comparator;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nl.mvdr.adventofcode.solver.IntSolver;
import nl.mvdr.adventofcode.adventofcode2019.intcode.Program;
import nl.mvdr.adventofcode.point.Point;

/**
 * Solution to the day 19 puzzle of 2019's Advent of Code:
 * <a href="https://adventofcode.com/2019/day/19">Tractor Beam</a>.
 *
 * @author Martijn van de Rijdt
 */
public class TractorBeamPart2 implements IntSolver {

    // Bounds for the search space, for both coordinates.
    // If this program results in a NoSuchElementException or an incorrect answer,
    // these bounds may need to be widened.
    // 
    // A value of 0 - 10_000 for both coordinates should be guaranteed to find the
    // correct answer, but also results in a long computation.
    private static final int LOWER_BOUND_X = 900;
    private static final int LOWER_BOUND_Y = 1_000;
    private static final int UPPER_BOUND_X = 1_000;
    private static final int UPPER_BOUND_Y = 1_300;
    
    private static final Logger LOGGER = LoggerFactory.getLogger(TractorBeamPart2.class);
    
    /**
     * {@inheritDoc}
     * 
     * @return value
     */
    @Override
    public int solve(Stream<String> lines) {
        Program program = Program.parse(lines.findFirst().orElseThrow());
        
        Point point = IntStream.range(LOWER_BOUND_X, UPPER_BOUND_X)
                .boxed()
                .parallel()
                .flatMap(x -> IntStream.range(LOWER_BOUND_Y, UPPER_BOUND_Y).mapToObj(y -> new Point(x.intValue(), y)))
                .filter(p -> fits(p, program))
                .peek(p -> LOGGER.debug("Point found: {}", p))
                .min(Comparator.comparing(Point::manhattanDistanceToOrigin))
                .orElseThrow();
        
          return point.x() * 10_000 + point.y();
    }
    
    /**
     * Determines whether the given point is the upper-left coordinate of a 100x100
     * square that fits within the tractor beam.
     * 
     * @param point   point to evaluate
     * @param program Intcode program from the puzzle input
     * @return whether the given point is the upper-left coordinate of a 100x100
     *         square that fits within the tractor beam
     */
    private boolean fits(Point point, Program program) {
        return TractorBeamUtil.isBeingPulled(point.translate(new Point(99, 0)), program)
                && TractorBeamUtil.isBeingPulled(point.translate(new Point(0, 99)), program);
    }

    /**
     * Main method.
     * 
     * @param args commandline arguments; these are ignored
     */
    public static void main(String[] args) {
        TractorBeamPart2 instance = new TractorBeamPart2();

        String result = instance.solve("input-day19-2019.txt");

        LOGGER.info(result);
    }
}
 