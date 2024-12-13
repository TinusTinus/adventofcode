package nl.mvdr.adventofcode.adventofcode2020.day07;

import java.util.List;
import java.util.stream.Stream;

import org.apache.commons.collections4.MultiSet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nl.mvdr.adventofcode.solver.IntSolver;

/**
 * Solution to the day 7 puzzle of 2020's Advent of Code:
 * <a href="https://adventofcode.com/2020/day/7">Handy Haversacks</a>.
 *
 * @author Martijn van de Rijdt
 */
public class HandyHaversacksPart2 implements IntSolver {

    private static final Logger LOGGER = LoggerFactory.getLogger(HandyHaversacksPart2.class);

    /**
     * {@inheritDoc}
     * 
     * @return how many individual bags are required inside a single shiny gold bag
     */
    @Override
    public int solve(Stream<String> lines) {
        List<Rule> rules = Rule.parse(lines);
        
        return numberOfBags("shiny gold", rules);
    }
    
    /**
     * Counts the number of bags required inside the given type of bag.
     * 
     * @param type type of bag (for example: shiny gold)
     * @param rules applicable luggage rules
     * @return number of bags required in the given type of bag
     */
    private int numberOfBags(String type, List<Rule> rules) {
        MultiSet<String> contents = rules.stream()
                .filter(rule -> rule.container().equals(type))
                .findFirst()
                .map(Rule::contents)
                .orElseThrow();
                
        return contents.entrySet()
                .stream()
                .mapToInt(entry -> entry.getCount() * (1 + numberOfBags(entry.getElement(), rules)))
                .sum();
        
    }

    /**
     * Main method.
     * 
     * @param args commandline arguments; these are ignored
     */
    public static void main(String[] args) {
        HandyHaversacksPart2 instance = new HandyHaversacksPart2();

        String result = instance.solve("input-day07-2020.txt");

        LOGGER.info(result);
    }
}
 