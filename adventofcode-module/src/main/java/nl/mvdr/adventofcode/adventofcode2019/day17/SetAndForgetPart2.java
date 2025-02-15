package nl.mvdr.adventofcode.adventofcode2019.day17;

import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nl.mvdr.adventofcode.solver.LongSolver;
import nl.mvdr.adventofcode.adventofcode2019.intcode.AsciiOutputDebugLogger;
import nl.mvdr.adventofcode.adventofcode2019.intcode.Program;

/**
 * Solution to the day 17 puzzle of 2019's Advent of Code:
 * <a href="https://adventofcode.com/2019/day/17">Set and Forget</a>.
 *
 * @author Martijn van de Rijdt
 */
public class SetAndForgetPart2 implements LongSolver {

    private static final Logger LOGGER = LoggerFactory.getLogger(SetAndForgetPart2.class);

    /**
     * {@inheritDoc}
     * 
     * @return amount of space dust collected
     */
    @Override
    public long solve(Stream<String> lines) {
        AsciiOutputDebugLogger outputLogger = new AsciiOutputDebugLogger();
        
        Program.parse(lines.findFirst().orElseThrow())
                .withAsciiInput(getProgramInput()) 
                .withOutput(outputLogger::handleOutput)
                .set(0, 2L)
                .execute();
        
        return outputLogger.getValue();
    }

    /** @return input for the Intcode program */
    private String getProgramInput() {
        // Movement functions determined by hand for my puzzle input
        String result = """
                        A,B,A,C,B,A,B,C,C,B
                        L,12,L,12,R,4
                        R,10,R,6,R,4,R,4
                        R,6,L,12,L,12
                        """;
        if (LOGGER.isDebugEnabled()) {
            // verbose
            result = result + "y\n";
        } else {
            // not so verbose
            result = result + "n\n";
        }
        return result;
    }
    
    /**
     * Main method.
     * 
     * @param args commandline arguments; these are ignored
     */
    public static void main(String[] args) {
        SetAndForgetPart2 instance = new SetAndForgetPart2();

        String result = instance.solve("input-day17-2019.txt");

        LOGGER.info(result);
    }
}
 