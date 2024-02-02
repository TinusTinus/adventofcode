package nl.mvdr.adventofcode.adventofcode2017.day18;

import java.util.List;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nl.mvdr.adventofcode.LongSolver;
import nl.mvdr.adventofcode.adventofcode2017.duet.Instruction;
import nl.mvdr.adventofcode.adventofcode2017.duet.Program;

/**
 * Solution to the day 18 puzzle of 2017's Advent of Code:
 * <a href="https://adventofcode.com/2017/day/18">Duet</a>.
 *
 * @author Martijn van de Rijdt
 */
public class DuetPart1 implements LongSolver {

    private static final Logger LOGGER = LoggerFactory.getLogger(DuetPart1.class);
    
    /**
     * {@inheritDoc}
     * 
     * @return recovered frequency
     */
    @Override
    public long solve(Stream<String> lines) {
        List<Instruction> instructions = Instruction.parseInstructions(lines, true);
        
        Program program = new Program(instructions);
        LOGGER.debug("Program: {}", program);
        
        program = program.executeInstructionsWhile(p -> p.getState().getRecoveredFrequency().isEmpty());
        
        LOGGER.debug("After program execution: {}", program);
        
        return program.getState().getRecoveredFrequency().orElseThrow();
    }
    
    /**
     * Main method.
     * 
     * @param args commandline arguments; these are ignored
     */
    public static void main(String[] args) {
        DuetPart1 instance = new DuetPart1();

        String result = instance.solve("input-day18-2017.txt");

        LOGGER.info(result);
    }
}
