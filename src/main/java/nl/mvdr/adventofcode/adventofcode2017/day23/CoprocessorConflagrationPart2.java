package nl.mvdr.adventofcode.adventofcode2017.day23;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nl.mvdr.adventofcode.PathSolver;
import nl.mvdr.adventofcode.adventofcode2017.duet.Instruction;
import nl.mvdr.adventofcode.adventofcode2017.duet.Program;
import nl.mvdr.adventofcode.adventofcode2017.duet.SetInstruction;

/**
 * Solution to the day 23 puzzle of 2017's Advent of Code:
 * <a href="https://adventofcode.com/2017/day/23">Coprocessor Conflagration</a>.
 *
 * @author Martijn van de Rijdt
 */
public class CoprocessorConflagrationPart2 implements PathSolver<Long> {

    private static final Logger LOGGER = LoggerFactory.getLogger(CoprocessorConflagrationPart2.class);
    
    /**
     * {@inheritDoc}
     * 
     * @return the value in register h after execution
     */
    @Override
    public Long solve(Path inputFilePath) throws IOException {
        List<Instruction> instructions = new ArrayList<>();
        instructions.add(new SetInstruction("a", "1"));
        instructions.addAll(Instruction.parseInstructions(inputFilePath, false));
        
        Program program = new Program(instructions);
        program = program.executeInstructions();
        
        return Long.valueOf(program.getState().getRegisterValue("h"));
    }
    
    /**
     * Main method.
     * 
     * @param args commandline arguments; these are ignored
     */
    public static void main(String[] args) {
        CoprocessorConflagrationPart2 instance = new CoprocessorConflagrationPart2();

        String result = instance.solve("input-day23-2017.txt");

        LOGGER.info(result);
    }
}
