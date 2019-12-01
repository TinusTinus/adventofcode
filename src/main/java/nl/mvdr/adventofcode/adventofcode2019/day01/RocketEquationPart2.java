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
public class RocketEquationPart2 implements IntSolver {

    private static final Logger LOGGER = LoggerFactory.getLogger(RocketEquationPart2.class);
    
    /**
     * {@inheritDoc}
     * 
     * @return sum of the fuel requirements
     */
    @Override
    public int solve(Stream<String> lines) {
        int totalMass = 0;
        
        int fuelMass = lines.filter(Predicate.not(String::isBlank))
                .mapToInt(Integer::parseInt)
                .map(this::computeFuelRequirement)
                .sum();
        
        while (0 < fuelMass) {
            totalMass += fuelMass;
            fuelMass = computeFuelRequirement(fuelMass);
        }
        
        return totalMass;
    }
    
    int computeFuelRequirement(int mass) {
        int result = mass / 3 - 2;
        
        LOGGER.info("The fuel requirement for a mass of {} is {}", Integer.valueOf(mass), Integer.valueOf(result)); // TODO debug
        
        return result;
    }

    /**
     * Main method.
     * 
     * @param args commandline arguments; these are ignored
     */
    public static void main(String[] args) {
        RocketEquationPart2 instance = new RocketEquationPart2();

        String result = instance.solve("input-day01-2019.txt");

        LOGGER.info(result);
    }
}
