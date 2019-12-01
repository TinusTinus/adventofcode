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
        return lines.filter(Predicate.not(String::isBlank))
                .mapToInt(Integer::parseInt)
                .map(RocketEquationPart2::computeTotalFuelRequirement)
                .sum();
    }
    
    /**
     * Computes the total fuel requirement for the given module.
     * 
     * The result of this method <em>does</em> include the fuel required to transport the fuel itself.
     * 
     * @param moduleMass mass of the module to be transported
     * @return total fuel requirement
     */
    private static int computeTotalFuelRequirement(int moduleMass) {
        int result = 0;
        
        int fuelMass = RocketEquationPart1.computeFuelRequirement(moduleMass);
        
        while (0 < fuelMass) {
            result += fuelMass;
            fuelMass = RocketEquationPart1.computeFuelRequirement(fuelMass);
        }
        
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
