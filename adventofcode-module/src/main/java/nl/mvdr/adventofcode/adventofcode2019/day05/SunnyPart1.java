package nl.mvdr.adventofcode.adventofcode2019.day05;

import java.util.ArrayList;
import java.util.List;
import java.util.function.LongConsumer;
import java.util.function.LongSupplier;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nl.mvdr.adventofcode.solver.LongSolver;
import nl.mvdr.adventofcode.adventofcode2019.intcode.Program;

/**
 * Solution to the day 5 puzzle of 2019's Advent of Code:
 * <a href="https://adventofcode.com/2019/day/5">Sunny with a Chance of Asteroids</a>.
 *
 * @author Martijn van de Rijdt
 */
public class SunnyPart1 implements LongSolver {

    private static final Logger LOGGER = LoggerFactory.getLogger(SunnyPart1.class);
    
    /**
     * {@inheritDoc}
     * 
     * @return value left at position 0 after the program halts
     */
    @Override
    public long solve(Stream<String> lines) {
        String programText = lines.findFirst().orElseThrow();
        
        LongSupplier input = () -> 1L;
        
        List<Long> outputValues = new ArrayList<>();
        LongConsumer output = outputValue -> outputValues.add(Long.valueOf(outputValue));
        
        Program program = Program.parse(programText, input, output);
        
        program = program.execute();

        boolean allTestsSuccessful = outputValues.stream()
                .limit(outputValues.size() - 1)
                .allMatch(outputValue -> outputValue.longValue() == 0L);
        if (!allTestsSuccessful) {
            throw new IllegalStateException("Diagnostic test failed, output: " + outputValues);
        }
        
        return outputValues.get(outputValues.size() - 1).longValue();
    }
    
    /**
     * Main method.
     * 
     * @param args commandline arguments; these are ignored
     */
    public static void main(String[] args) {
        SunnyPart1 instance = new SunnyPart1();

        String result = instance.solve("input-day05-2019.txt");

        LOGGER.info(result);
    }
}
