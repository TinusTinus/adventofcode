package nl.mvdr.adventofcode.adventofcode2019.day19;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nl.mvdr.adventofcode.LongSolver;
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
                .flatMap(x -> IntStream.range(0, 50).mapToObj(y -> new Point(x.intValue(), y)))
                .filter(point -> isBeingPulled(point, program))
                .count();
    }
    
    /**
     * Determines whether the drone is being affected by the tractor beam.
     * 
     * @param drone location of the drone
     * @param program Intcode program from the puzzle input
     * @return whether the drone is being pulled
     */
    private boolean isBeingPulled(Point drone, Program program) {
        Queue<Long> inputQueue = new LinkedList<>();
        inputQueue.offer(Long.valueOf(drone.getX()));
        inputQueue.offer(Long.valueOf(drone.getY()));
        List<Long> outputList = new ArrayList<>(1);
        
        program.withInput(inputQueue::poll)
                .withOutput(outputList::add)
                .execute();
        
        if (outputList.size() != 1) {
            throw new IllegalStateException("Unexpected output received from intcode program: " + outputList);
        }
        
        return outputList.get(0).longValue() == 1L;
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
 