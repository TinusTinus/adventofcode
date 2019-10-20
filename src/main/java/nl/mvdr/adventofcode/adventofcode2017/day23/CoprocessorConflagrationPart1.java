package nl.mvdr.adventofcode.adventofcode2017.day23;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nl.mvdr.adventofcode.PathSolver;
import nl.mvdr.adventofcode.adventofcode2017.duet.Instruction;
import nl.mvdr.adventofcode.adventofcode2017.duet.State;

/**
 * Solution to the day 23 puzzle of 2017's Advent of Code:
 * <a href="https://adventofcode.com/2017/day/23">Coprocessor Conflagration</a>.
 *
 * @author Martijn van de Rijdt
 */
public class CoprocessorConflagrationPart1 implements PathSolver<Integer> {

    private static final Logger LOGGER = LoggerFactory.getLogger(CoprocessorConflagrationPart1.class);
    
    /**
     * {@inheritDoc}
     * 
     * @return how many times the mul instruction is invoked
     */
    @Override
    public Integer solve(Path inputFilePath) throws IOException {
        List<Instruction> instructions = Instruction.parseInstructions(inputFilePath, false);
        State state = new State();
        
        // TODO execute the instructions until the program can no longer proceed, and retrieve the number of multiplications
        
        return null; // TODO
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
