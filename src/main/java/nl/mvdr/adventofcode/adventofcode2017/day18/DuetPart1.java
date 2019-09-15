package nl.mvdr.adventofcode.adventofcode2017.day18;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nl.mvdr.adventofcode.PathSolver;

/**
 * Solution to the day 18 puzzle of 2017's Advent of Code:
 * <a href="https://adventofcode.com/2017/day/18">Duet</a>.
 *
 * @author Martijn van de Rijdt
 */
public class DuetPart1 implements PathSolver<Integer> {

    private static final Logger LOGGER = LoggerFactory.getLogger(DuetPart1.class);
    
    /**
     * {@inheritDoc}
     * 
     * @return recovered frequency
     */
    @Override
    public Integer solve(Path inputFilePath) throws IOException {
        List<Instruction> instructions = Instruction.parseInstructions(inputFilePath);
        State state = new State();
        
        while (state.getRecoveredFrequency().isEmpty()) {
            Instruction instruction = instructions.get(state.getInstructionPointer());
            state = instruction.execute(state);
        }
        
        return Integer.valueOf(state.getRecoveredFrequency().getAsInt());
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
