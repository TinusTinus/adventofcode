package nl.mvdr.adventofcode.adventofcode2019.day14;

import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nl.mvdr.adventofcode.LongSolver;

/**
 * Solution to the day 14 puzzle of 2019's Advent of Code:
 * <a href="https://adventofcode.com/2019/day/14">Space Stoichiometry</a>.
 *
 * @author Martijn van de Rijdt
 */
public class SpaceStoichiometryPart1 implements LongSolver {

    private static final Logger LOGGER = LoggerFactory.getLogger(SpaceStoichiometryPart1.class);

    /**
     * {@inheritDoc}
     * 
     * @return the minimum amount of ORE required to produce exactly 1 FUEL
     */
    @Override
    public long solve(Stream<String> lines) {
        Set<Reaction> reactions = lines.filter(Predicate.not(String::isBlank)).map(Reaction::parse)
                .collect(Collectors.toSet());

        LOGGER.debug("Reactions: {}", reactions);

        return Reaction.computeRequiredOreForFuel(1, reactions);
    }

    /**
     * Main method.
     * 
     * @param args commandline arguments; these are ignored
     */
    public static void main(String[] args) {
        SpaceStoichiometryPart1 instance = new SpaceStoichiometryPart1();

        String result = instance.solve("input-day14-2019.txt");

        LOGGER.info(result);
    }
}
 