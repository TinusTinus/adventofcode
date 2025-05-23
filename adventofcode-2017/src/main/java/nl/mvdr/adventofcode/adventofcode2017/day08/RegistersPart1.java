package nl.mvdr.adventofcode.adventofcode2017.day08;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nl.mvdr.adventofcode.solver.IntSolver;

/**
 * Solution to the day 8 puzzle of 2017's Advent of Code:
 * <a href="https://adventofcode.com/2017/day/8">I Heard You Like Registers</a>.
 *
 * @author Martijn van de Rijdt
 */
public class RegistersPart1 implements IntSolver {

    private static final Logger LOGGER = LoggerFactory.getLogger(RegistersPart1.class);
    
    /**
     * {@inheritDoc}
     * 
     * @return the largest value in any register after completing the instructions in the puzzle input
     */
    @Override
    public int solve(Stream<String> lines) {
        List<Instruction> instructions = Instruction.parse(lines);
        
        Map<String, Integer> registers = new HashMap<>();
        instructions.forEach(instruction -> instruction.accept(registers));
        
        return registers.values().stream()
                .mapToInt(Integer::intValue)
                .max()
                .orElse(0);
    }
    
    /**
     * Main method.
     * 
     * @param args commandline arguments; these are ignored
     */
    public static void main(String[] args) {
        RegistersPart1 instance = new RegistersPart1();

        String result = instance.solve("input-day08-2017.txt");

        LOGGER.info(result);
    }
}
