package nl.mvdr.adventofcode.adventofcode2019.day12;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import org.apache.commons.math3.util.ArithmeticUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nl.mvdr.adventofcode.LongSolver;
import nl.mvdr.adventofcode.point.Axis3D;

/**
 * Solution to the day 12 puzzle of 2019's Advent of Code:
 * <a href="https://adventofcode.com/2019/day/12">The N-Body Problem</a>.
 *
 * @author Martijn van de Rijdt
 */
public class NBodyProblemPart2 implements LongSolver {

    private static final Logger LOGGER = LoggerFactory.getLogger(NBodyProblemPart2.class);
    
    /**
     * {@inheritDoc}
     * 
     * @return number of simulation steps
     */
    @Override
    public long solve(Stream<String> lines) {
        List<Moon> initialSystem = lines.filter(Predicate.not(String::isBlank))
                .map(Moon::parse)
                .collect(Collectors.toList());
        LOGGER.debug("System: {}", initialSystem);
        
        // Values cycle on axes independently.
        Map<Axis3D, Long> repetitions = new HashMap<>();
        
        List<Moon> system = Moon.performSimulationStep(initialSystem);
        long reps = 1L;
        while (repetitions.size() != 3) {
            system = Moon.performSimulationStep(system);
            reps++;
            
            for (Axis3D axis : Axis3D.values()) {
                if (!repetitions.containsKey(axis) && equalsOnAxis(initialSystem, system, axis)) {
                    LOGGER.debug("Repeat on axis {} after {} steps", axis, Long.valueOf(reps));
                    repetitions.put(axis, Long.valueOf(reps));
                }
            }
        }
        
        LOGGER.debug("Repetitions: {}", repetitions.values());
        
        return leastCommonMultiple(repetitions.values());
    }
    
    private boolean equalsOnAxis(List<Moon> system0, List<Moon> system1, Axis3D axis) {
        return IntStream.range(0, system0.size())
                .allMatch(i -> system0.get(i).equalsOnAxis(system1.get(i), axis));
    }
    
    /**
     * Computes the least common multiple (LCM) of the given collection of values.
     * 
     * @param values values
     * @return LCM
     */
    private long leastCommonMultiple(Collection<Long> values) {
        // Note: I'm not convinced that, in general:
        //    lcm(a, b, c) = lcm((lcm(a, b), c)
        // But apparently this works for our inputs.
        Iterator<Long> iterator = values.iterator();
        long result = 1L;
        while(iterator.hasNext()) {
            result = ArithmeticUtils.lcm(iterator.next().longValue(), result);
        }
        return result;
    }
    
    /**
     * Main method.
     * 
     * @param args commandline arguments; these are ignored
     */
    public static void main(String[] args) {
        NBodyProblemPart2 instance = new NBodyProblemPart2();

        String result = instance.solve("input-day12-2019.txt");

        LOGGER.info(result);
    }
}
