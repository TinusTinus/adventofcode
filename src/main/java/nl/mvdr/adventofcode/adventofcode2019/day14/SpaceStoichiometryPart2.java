package nl.mvdr.adventofcode.adventofcode2019.day14;

import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nl.mvdr.adventofcode.IntSolver;

/**
 * Solution to the day 14 puzzle of 2019's Advent of Code:
 * <a href="https://adventofcode.com/2019/day/14">Space Stoichiometry</a>.
 *
 * @author Martijn van de Rijdt
 */
public class SpaceStoichiometryPart2 implements IntSolver {

    private static final long TRILLION = 1000000000000L;
    private static final Logger LOGGER = LoggerFactory.getLogger(SpaceStoichiometryPart2.class);

    /**
     * {@inheritDoc}
     * 
     * @return the maximum amount of FUEL that can be produced with 1 trillion ORE
     */
    @Override
    public int solve(Stream<String> lines) {
        Set<Reaction> reactions = lines.filter(Predicate.not(String::isBlank)).map(Reaction::parse)
                .collect(Collectors.toSet());
        LOGGER.debug("Reactions: {}", reactions);

        // Binary search for the answer
        int low = Math.toIntExact(TRILLION / Reaction.computeRequiredOreForFuel(1, reactions));
        int high = Integer.MAX_VALUE;

        while (low <= high) {
            int mid = (low + high) >>> 1;
            
            long requiredOre = Reaction.computeRequiredOreForFuel(mid, reactions);
            
            LOGGER.debug("{} fuel requires {} ore", Integer.valueOf(mid), Long.valueOf(requiredOre));
            
            if (requiredOre < TRILLION) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return high;
    }

    /**
     * Main method.
     * 
     * @param args commandline arguments; these are ignored
     */
    public static void main(String[] args) {
        SpaceStoichiometryPart2 instance = new SpaceStoichiometryPart2();

        String result = instance.solve("input-day14-2019.txt");

        LOGGER.info(result);
    }
}
 