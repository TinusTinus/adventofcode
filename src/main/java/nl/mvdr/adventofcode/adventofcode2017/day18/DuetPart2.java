package nl.mvdr.adventofcode.adventofcode2017.day18;

import java.io.IOException;
import java.nio.file.Path;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nl.mvdr.adventofcode.PathSolver;
import nl.mvdr.adventofcode.adventofcode2017.duet.Instruction;
import nl.mvdr.adventofcode.adventofcode2017.duet.State;

/**
 * Solution to the day 18 puzzle of 2017's Advent of Code:
 * <a href="https://adventofcode.com/2017/day/18">Duet</a>.
 *
 * @author Martijn van de Rijdt
 */
public class DuetPart2 implements PathSolver<Integer> {

    private static final Logger LOGGER = LoggerFactory.getLogger(DuetPart2.class);
    
    /**
     * {@inheritDoc}
     * 
     * @return number of times program 1 sent a message
     */
    @Override
    public Integer solve(Path inputFilePath) throws IOException {
        List<Instruction> instructions = Instruction.parseInstructions(inputFilePath, false);
        
        Queue<Long> queue0 = new LinkedList<>();
        Queue<Long> queue1 = new LinkedList<>();
        
        State state0 = new State(0, queue0, queue1);
        State state1 = new State(1, queue1, queue0);
        
        while (canProceed(state0, instructions) || canProceed(state1, instructions)) {
            while (canProceed(state0, instructions)) {
                Instruction instruction = instructions.get(state0.getInstructionPointer());
                LOGGER.debug("0: {} - {}", state0, instruction);
                state0 = instruction.execute(state0);
            }
            
            while (canProceed(state1, instructions)) {
                Instruction instruction = instructions.get(state1.getInstructionPointer());
                LOGGER.debug("1: {} - {}", state1, instruction);
                state1 = instruction.execute(state1);
            }
        }
        LOGGER.debug("End state 0: {}", state0);
        LOGGER.debug("End state 1: {}", state1);
        
        return Integer.valueOf(state1.getTimesSent());
    }
    
    private boolean canProceed(State state, List<Instruction> instructions) {
        boolean result = 0 <= state.getInstructionPointer();
        result = result && state.getInstructionPointer() < instructions.size();
        result = result && instructions.get(state.getInstructionPointer()).canProceed(state);
        return result;
    }
    
    /**
     * Main method.
     * 
     * @param args commandline arguments; these are ignored
     */
    public static void main(String[] args) {
        DuetPart2 instance = new DuetPart2();

        String result = instance.solve("input-day18-2017.txt");

        LOGGER.info(result);
    }
}
