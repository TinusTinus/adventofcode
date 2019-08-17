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
public class TrampolinesPart1 implements PathSolver<Integer> {

    private static final Logger LOGGER = LoggerFactory.getLogger(TrampolinesPart1.class);
    
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
            instructionPointer = instructionPointer + instructions.get(instructionPointer).intValue();
            instructions.set(previousInstructionPointer, Integer.valueOf(instructions.get(previousInstructionPointer).intValue() + 1));
            
            steps++;
            
            LOGGER.debug("Instruction pointer: {}, instructions: {}", Integer.valueOf(instructionPointer), instructions);
        }
        
        return Integer.valueOf(steps);
    }

    /**
     * Main method.
     * 
     * @param args commandline arguments; these are ignored
     */
    public static void main(String[] args) {
        TrampolinesPart1 instance = new TrampolinesPart1();

        String result = instance.solve("input-day05-2017.txt");

        LOGGER.info(result);
    }
}
