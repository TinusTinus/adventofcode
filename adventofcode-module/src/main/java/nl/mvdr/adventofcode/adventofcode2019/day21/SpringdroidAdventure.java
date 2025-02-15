package nl.mvdr.adventofcode.adventofcode2019.day21;

import java.util.stream.Stream;

import nl.mvdr.adventofcode.solver.LongSolver;
import nl.mvdr.adventofcode.adventofcode2019.intcode.AsciiOutputDebugLogger;
import nl.mvdr.adventofcode.adventofcode2019.intcode.Program;

/**
 * Solution to the day 21 puzzle of 2019's Advent of Code:
 * <a href="https://adventofcode.com/2019/day/21">Springdroid Adventure</a>.
 *
 * @author Martijn van de Rijdt
 */
abstract class SpringdroidAdventure implements LongSolver {

    /**
     * {@inheritDoc}
     * 
     * @return amount of hull damage
     */
    @Override
    public long solve(Stream<String> lines) {
        AsciiOutputDebugLogger outputLogger = new AsciiOutputDebugLogger();
        
        Program.parse(lines.findFirst().orElseThrow())
                .withAsciiInput(getSpringscript())
                .withOutput(outputLogger::handleOutput)
                .execute();
        
        return outputLogger.getValue();
    }
    
    /** @return input for the springdroid */
    abstract String getSpringscript();
}
 