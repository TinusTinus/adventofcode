package nl.mvdr.adventofcode.adventofcode2017.day23;

import java.util.List;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nl.mvdr.adventofcode.IntSolver;
import nl.mvdr.adventofcode.adventofcode2017.duet.Instruction;
import nl.mvdr.adventofcode.adventofcode2017.duet.MultiplyInstruction;
import nl.mvdr.adventofcode.adventofcode2017.duet.Program;

/**
 * Solution to the day 23 puzzle of 2017's Advent of Code:
 * <a href="https://adventofcode.com/2017/day/23">Coprocessor Conflagration</a>.
 *
 * @author Martijn van de Rijdt
 */
public class CoprocessorConflagrationPart1 implements IntSolver {

    private static final Logger LOGGER = LoggerFactory.getLogger(CoprocessorConflagrationPart1.class);
    
    /**
     * {@inheritDoc}
     * 
     * @return how many times the mul instruction is invoked
     */
    @Override
    public int solve(Stream<String> lines) {
        List<Instruction> instructions = Instruction.parseInstructions(lines, false);
        Program program = new Program(instructions);
        program = program.executeInstructions();
        
        return program.timesExecuted(MultiplyInstruction.class);
    }
    
    /**
     * Main method.
     * 
     * @param args commandline arguments; these are ignored
     */
    public static void main(String[] args) {
        CoprocessorConflagrationPart1 instance = new CoprocessorConflagrationPart1();

        String result = instance.solve("input-day23-2017.txt");

        LOGGER.info(result);
    }
}
