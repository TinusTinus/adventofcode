package nl.mvdr.adventofcode.adventofcode2017.day08;

import java.io.IOException;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nl.mvdr.adventofcode.PathSolver;

/**
 * Solution to the day 8 puzzle of 2017's Advent of Code:
 * <a href="https://adventofcode.com/2017/day/8">I Heard You Like Registers</a>.
 *
 * @author Martijn van de Rijdt
 */
public class RegistersPart2 implements PathSolver<Integer> {

    private static final Logger LOGGER = LoggerFactory.getLogger(RegistersPart2.class);
    
    /**
     * {@inheritDoc}
     * 
     * @return the largest value in any register during execution of the instructions in the puzzle input
     */
    @Override
    public Integer solve(Path inputFilePath) throws IOException {
        List<Instruction> instructions = Instruction.parse(inputFilePath);
        
        Map<String, Integer> registers = new HashMap<>();
        int result = 0;
        for (Instruction instruction : instructions) {
            instruction.accept(registers);
            
            int currentMaximum = registers.values().stream()
                .mapToInt(Integer::intValue)
                .max()
                .orElse(0);
            result = Math.max(result, currentMaximum);
        }
        
        return Integer.valueOf(result);
    }
    
    /**
     * Main method.
     * 
     * @param args commandline arguments; these are ignored
     */
    public static void main(String[] args) {
        RegistersPart2 instance = new RegistersPart2();

        String result = instance.solve("input-day08-2017.txt");

        LOGGER.info(result);
    }
}
