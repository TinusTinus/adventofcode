package nl.mvdr.adventofcode.adventofcode2017.day05;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nl.mvdr.adventofcode.solver.IntSolver;

/**
 * Solution to the day 5 puzzle of 2017's Advent of Code:
 * <a href="https://adventofcode.com/2017/day/5">A Maze of Twisty Trampolines, All Alike</a>.
 *
 * @author Martijn van de Rijdt
 */
abstract class Trampolines implements IntSolver {

    private static final Logger LOGGER = LoggerFactory.getLogger(Trampolines.class);
    
    /**
     * {@inheritDoc}
     * 
     * @return number of steps
     */
    @Override
    public int solve(Stream<String> lines) {
        List<Integer> instructions = lines.filter(Predicate.not(String::isBlank))
                // parse each line to an integer
                .mapToInt(Integer::parseInt)
                .boxed()
                .collect(ArrayList::new, List::add, List::addAll);
        
        LOGGER.debug("Instruction pointer: 0, instructions: {}", instructions);
        
        int instructionPointer = 0;
        int steps = 0;
        
        while (0 <= instructionPointer && instructionPointer < instructions.size()) {
            int previousInstructionPointer = instructionPointer;
            
            // Update the instruction pointer
            int offset = instructions.get(instructionPointer).intValue();
            instructionPointer = instructionPointer + offset;
            
            // Update the offset
            int newOffset;
            if (increase(offset)) {
                newOffset = offset + 1;
            } else {
                newOffset = offset - 1;
            }
            instructions.set(previousInstructionPointer, Integer.valueOf(newOffset));
            
            steps++;
            
            LOGGER.debug("Instruction pointer: {}, instructions: {}", Integer.valueOf(instructionPointer), instructions);
        }
        
        return steps;
    }

    /**
     * Whether the offset value should be increased by one. If false it should be decreased by one.
     * 
     * @param offset offset value
     * @return whether to increase
     */
    abstract boolean increase(int offset);
}
