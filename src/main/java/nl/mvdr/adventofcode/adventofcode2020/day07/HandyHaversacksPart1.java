package nl.mvdr.adventofcode.adventofcode2020.day07;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nl.mvdr.adventofcode.IntSolver;

/**
 * Solution to the day 7 puzzle of 2020's Advent of Code:
 * <a href="https://adventofcode.com/2020/day/7">Handy Haversacks</a>.
 *
 * @author Martijn van de Rijdt
 */
public class HandyHaversacksPart1 implements IntSolver {

    private static final Logger LOGGER = LoggerFactory.getLogger(HandyHaversacksPart1.class);

    /**
     * {@inheritDoc}
     * 
     * @return how many bag colors can eventually contain at least one shiny gold bag
     */
    @Override
    public int solve(Stream<String> lines) {
        List<Rule> rules = Rule.parse(lines);
        
        Set<String> types = new HashSet<>();
        types.add("shiny gold");
        Set<String> foundTypes = new HashSet<>();
        
        while (!types.isEmpty()) {
            Set<String> newTypes = rules.stream()
                    .filter(rule -> types.stream().anyMatch(rule.contents()::contains))
                    .map(Rule::container)
                    .filter(Predicate.not(foundTypes::contains))
                    .collect(Collectors.toSet());
            foundTypes.addAll(types);
            types.clear();
            types.addAll(newTypes);
        }
        
        foundTypes.remove("shiny gold");
        
        return foundTypes.size();
    }

    /**
     * Main method.
     * 
     * @param args commandline arguments; these are ignored
     */
    public static void main(String[] args) {
        HandyHaversacksPart1 instance = new HandyHaversacksPart1();

        String result = instance.solve("input-day07-2020.txt");

        LOGGER.info(result);
    }
}
 