package nl.mvdr.adventofcode.adventofcode2019.day12;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
        
        // Values cycle for each moon independently on individual axes.
        // TODO or only on axis?
        Map<MoonAndAxis, Long> repetitions = new HashMap<>();
        
        List<Moon> system = Moon.performSimulationStep(initialSystem);
        long reps = 1L;
        while (repetitions.size() != initialSystem.size() * 3) {
            system = Moon.performSimulationStep(system);
            reps++;
            
            for (int i = 0; i != system.size(); i++) {
                for (Axis3D axis : Axis3D.values()) {
                    if (!repetitions.containsKey(new MoonAndAxis(initialSystem.get(i), axis)) 
                            && system.get(i).equalsOnAxis(initialSystem.get(i), axis)) {
                        LOGGER.debug("Moon {} has repeated itself on axis {} after {} steps", Integer.valueOf(i), axis, Long.valueOf(reps));
                        repetitions.put(new MoonAndAxis(initialSystem.get(i), axis), Long.valueOf(reps));
                    }
                }
            }
        }
        
        LOGGER.info("Repetitions: {}", repetitions.values());
        
        return leastCommonMultiple(repetitions.values());
    }
    
    /**
     * Computes the least common multiple (LCM) of the given collection of values.
     * 
     * @param values values
     * @return LCM
     */
    private long leastCommonMultiple(Collection<Long> values) {
        // TODO
        return 0L;
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
