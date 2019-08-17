package nl.mvdr.adventofcode.adventofcode2017.day05;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nl.mvdr.adventofcode.PathSolver;

/**
 * Solution to the day 5 puzzle of 2017's Advent of Code:
 * <a href="https://adventofcode.com/2018/day/5">A Maze of Twisty Trampolines, All Alike</a>.
 *
 * @author Martijn van de Rijdt
 */
abstract class Trampolines implements PathSolver<Integer> {

    private static final Logger LOGGER = LoggerFactory.getLogger(Trampolines.class);
    
    /**
     * {@inheritDoc}
     * 
     * @return number of steps
     */
    @Override
    public Integer solve(Path inputFilePath) throws IOException {
        List<Integer> instructions = Files.lines(inputFilePath)
                // ignore empty lines (the last line in the file)
                .filter(Objects::nonNull)
                .filter(line -> !line.isBlank())
                // parse each line to an integer
                .mapToInt(Integer::parseInt)
                .boxed()
                .collect(ArrayList::new, List::add, List::addAll);
        
        LOGGER.debug("Instruction pointer: 0, instructions: {}", instructions);
        
        int instructionPointer = 0;
        int steps = 0;
        
        while (0 <= instructionPointer && instructionPointer < instructions.size()) {
            int previousInstructionPointer = instructionPointer;
            int offset = instructions.get(instructionPointer).intValue();
            instructionPointer = instructionPointer + offset;
            
            int newOffset;
            if (increase(offset)) {
                newOffset = instructions.get(previousInstructionPointer).intValue() + 1;
            } else {
                newOffset = instructions.get(previousInstructionPointer).intValue() - 1;
            }
            instructions.set(previousInstructionPointer, Integer.valueOf(newOffset));
            
            steps++;
            
            LOGGER.debug("Instruction pointer: {}, instructions: {}", Integer.valueOf(instructionPointer), instructions);
        }
        
        return Integer.valueOf(steps);
    }

    /**
     * Whether the offset value should be increased by one. If false it should be decreased by one.
     * 
     * @param offset offset value
     * @return whether to increase
     */
    abstract boolean increase(int offset);
}
