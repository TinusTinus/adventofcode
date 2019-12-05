package nl.mvdr.adventofcode.adventofcode2019.day05;

import java.util.ArrayList;
import java.util.List;
import java.util.function.IntConsumer;
import java.util.function.IntSupplier;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nl.mvdr.adventofcode.IntSolver;
import nl.mvdr.adventofcode.adventofcode2019.intcode.Program;

/**
 * Solution to the day 5 puzzle of 2019's Advent of Code:
 * <a href="https://adventofcode.com/2019/day/5">Sunny with a Chance of Asteroids</a>.
 *
 * @author Martijn van de Rijdt
 */
public class SunnyPart2 implements IntSolver {

    private static final Logger LOGGER = LoggerFactory.getLogger(SunnyPart2.class);
    
    /**
     * {@inheritDoc}
     * 
     * @return value left at position 0 after the program halts
     */
    @Override
    public int solve(Stream<String> lines) {
        String programText = lines.findFirst().orElseThrow();
        
        IntSupplier input = () -> 5;
        
        List<Integer> outputValues = new ArrayList<>();
        IntConsumer output = outputValue -> outputValues.add(Integer.valueOf(outputValue));
        
        Program program = Program.parse(programText, input, output);
        
        program = program.execute();

        if (outputValues.size() != 1) {
            throw new IllegalStateException("Unexpected output: " + outputValues);
        }
        
        return outputValues.get(0).intValue();
    }
    
    /**
     * Main method.
     * 
     * @param args commandline arguments; these are ignored
     */
    public static void main(String[] args) {
        SunnyPart2 instance = new SunnyPart2();

        String result = instance.solve("input-day05-2019.txt");

        LOGGER.info(result);
    }
}
