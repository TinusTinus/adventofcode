package nl.mvdr.adventofcode.adventofcode2019.day01;

import java.util.function.Predicate;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nl.mvdr.adventofcode.IntSolver;

/**
 * Solution to the day 1 puzzle of 2019's Advent of Code:
 * <a href="https://adventofcode.com/2019/day/1">The Tyranny of the Rocket Equation</a>.
 *
 * @author Martijn van de Rijdt
 */
public class RocketEquationPart1 implements IntSolver {

    private static final Logger LOGGER = LoggerFactory.getLogger(RocketEquationPart1.class);
    
    /**
     * {@inheritDoc}
     * 
     * @return sum of the fuel requirements
     */
    @Override
    public int solve(Stream<String> lines) {
        return lines.filter(Predicate.not(String::isBlank))
                .mapToInt(Integer::parseInt)
                .map(RocketEquationPart1::computeFuelRequirement)
                .sum();
    }
    
    /**
     * Computes the fuel requirement for the given mass.
     * 
     * The result of this method does <em>not</em> include the fuel required to transport the fuel itself.
     * 
     * @param mass mass
     * @return fuel requirement
     */
    static int computeFuelRequirement(int mass) {
        int result = mass / 3 - 2;
        
        LOGGER.debug("The fuel requirement for a mass of {} is {}", Integer.valueOf(mass), Integer.valueOf(result));
        
        return result;
    }

    /**
     * Main method.
     * 
     * @param args commandline arguments; these are ignored
     */
    public static void main(String[] args) {
        RocketEquationPart1 instance = new RocketEquationPart1();

        String result = instance.solve("input-day01-2019.txt");

        LOGGER.info(result);
    }
}
