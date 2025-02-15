package nl.mvdr.adventofcode.adventofcode2019.day19;

import java.util.stream.IntStream;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nl.mvdr.adventofcode.solver.LongSolver;
import nl.mvdr.adventofcode.adventofcode2019.intcode.Program;
import nl.mvdr.adventofcode.point.Point;

/**
 * Solution to the day 19 puzzle of 2019's Advent of Code:
 * <a href="https://adventofcode.com/2019/day/19">Tractor Beam</a>.
 *
 * @author Martijn van de Rijdt
 */
public class TractorBeamPart1 implements LongSolver {

    private static final Logger LOGGER = LoggerFactory.getLogger(TractorBeamPart1.class);

    /**
     * {@inheritDoc}
     * 
     * @return how many points are affected by the tractor beam in the 50x50 area closest to the emitter
     */
    @Override
    public long solve(Stream<String> lines) {
        Program program = Program.parse(lines.findFirst().orElseThrow());
        
        return IntStream.range(0, 50)
                .boxed()
                .parallel()
                .flatMap(x -> IntStream.range(0, 50).mapToObj(y -> new Point(x.intValue(), y)))
                .filter(point -> TractorBeamUtil.isBeingPulled(point, program))
                .count();
    }
    
    /**
     * Main method.
     * 
     * @param args commandline arguments; these are ignored
     */
    public static void main(String[] args) {
        TractorBeamPart1 instance = new TractorBeamPart1();

        String result = instance.solve("input-day19-2019.txt");

        LOGGER.info(result);
    }
}
 