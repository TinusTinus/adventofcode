package nl.mvdr.adventofcode.adventofcode2019.day12;

import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nl.mvdr.adventofcode.IntSolver;

/**
 * Solution to the day 12 puzzle of 2019's Advent of Code:
 * <a href="https://adventofcode.com/2019/day/12">The N-Body Problem</a>.
 *
 * @author Martijn van de Rijdt
 */
public class NBodyProblemPart1 implements IntSolver {

    private static final Logger LOGGER = LoggerFactory.getLogger(NBodyProblemPart1.class);
    
    private final int steps;
    
    /**
     * Constructor.
     * 
     * @param steps number of simulation steps to run
     */
    NBodyProblemPart1(int steps) {
        super();
        this.steps = steps;
    }
    
    /** Constructor. */
    public NBodyProblemPart1() {
        this(1_000);
    }
    
    /**
     * {@inheritDoc}
     * 
     * @return total energy in the system after simulating the moons for 1000 steps
     */
    @Override
    public int solve(Stream<String> lines) {
        Set<Moon> system = lines.filter(Predicate.not(String::isBlank))
                .map(Moon::parse)
                .collect(Collectors.toSet());
        LOGGER.debug("Moons: {}", system);
        
        for (int i = 0; i != steps; i++) {
            // TODO perform a simulation step
        }
        
        return system.stream()
                .mapToInt(Moon::computeTotalEnergy)
                .sum();
    }
    
    /**
     * Main method.
     * 
     * @param args commandline arguments; these are ignored
     */
    public static void main(String[] args) {
        NBodyProblemPart1 instance = new NBodyProblemPart1();

        String result = instance.solve("input-day12-2019.txt");

        LOGGER.info(result);
    }
}
