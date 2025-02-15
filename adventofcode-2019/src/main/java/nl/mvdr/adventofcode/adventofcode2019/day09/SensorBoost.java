package nl.mvdr.adventofcode.adventofcode2019.day09;

import java.util.ArrayList;
import java.util.List;
import java.util.function.LongConsumer;
import java.util.function.LongSupplier;
import java.util.stream.Stream;

import nl.mvdr.adventofcode.solver.LongSolver;
import nl.mvdr.adventofcode.adventofcode2019.intcode.Program;

/**
 * Solution to the day 9 puzzle of 2019's Advent of Code:
 * <a href="https://adventofcode.com/2019/day/9">Sensor Boost</a>.
 *
 * @author Martijn van de Rijdt
 */
abstract class SensorBoost implements LongSolver {

    private final long inputValue;
    
    /**
     * Constructor.
     * 
     * @param inputValue the input value for the BOOST program
     */
    SensorBoost(long inputValue) {
        super();
        this.inputValue = inputValue;
    }
    
    /**
     * {@inheritDoc}
     * 
     * @return value left at position 0 after the program halts
     */
    @Override
    public long solve(Stream<String> lines) {
        String programText = lines.findFirst().orElseThrow();
        
        LongSupplier input = () -> inputValue;
        
        List<Long> outputValues = new ArrayList<>();
        LongConsumer output = outputValue -> outputValues.add(Long.valueOf(outputValue));
        
        Program program = Program.parse(programText, input, output);
        program.execute();

        if (outputValues.size() != 1) {
            throw new IllegalStateException("BOOST output: " + outputValues);
        }
        
        return outputValues.get(0).longValue();
    }
}
