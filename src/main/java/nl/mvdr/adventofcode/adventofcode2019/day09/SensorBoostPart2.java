package nl.mvdr.adventofcode.adventofcode2019.day09;

import java.util.ArrayList;
import java.util.List;
import java.util.function.LongConsumer;
import java.util.function.LongSupplier;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nl.mvdr.adventofcode.LongSolver;
import nl.mvdr.adventofcode.adventofcode2019.intcode.Program;

/**
 * Solution to the day 9 puzzle of 2019's Advent of Code:
 * <a href="https://adventofcode.com/2019/day/9">Sensor Boost</a>.
 *
 * @author Martijn van de Rijdt
 */
public class SensorBoostPart2 implements LongSolver {

    private static final Logger LOGGER = LoggerFactory.getLogger(SensorBoostPart2.class);
    
    /**
     * {@inheritDoc}
     * 
     * @return value left at position 0 after the program halts
     */
    @Override
    public long solve(Stream<String> lines) {
        String programText = lines.findFirst().orElseThrow();
        
        LongSupplier input = () -> 2L;
        
        List<Long> outputValues = new ArrayList<>();
        LongConsumer output = outputValue -> outputValues.add(Long.valueOf(outputValue));
        
        Program program = Program.parse(programText, input, output);
        program.execute();

        if (outputValues.size() != 1) {
            throw new IllegalStateException("BOOST output: " + outputValues);
        }
        
        return outputValues.get(0).longValue();
    }
    
    /**
     * Main method.
     * 
     * @param args commandline arguments; these are ignored
     */
    public static void main(String[] args) {
        SensorBoostPart2 instance = new SensorBoostPart2();

        String result = instance.solve("input-day09-2019.txt");

        LOGGER.info(result);
    }
}
