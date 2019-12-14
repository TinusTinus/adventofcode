package nl.mvdr.adventofcode.adventofcode2019.day14;

import java.util.HashMap;
import java.util.Map;
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
        
        int result = 0;
        long remainingOre = 1000000000000L;
        boolean done = false;
        Map<String, Integer> leftovers = new HashMap<>();
        
        while(!done) {
            int requiredOre = Reaction.computeRequiredOreForFuel(reactions, leftovers);
            if (requiredOre <= remainingOre) {
                result++;
                remainingOre -= requiredOre;
                LOGGER.debug("Fuel created: {}, remaining ore: {}, leftovers: {}", Integer.valueOf(result),
                        Long.valueOf(remainingOre), leftovers);
            } else {
                done = true;
            }
        }

        return result;
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
 