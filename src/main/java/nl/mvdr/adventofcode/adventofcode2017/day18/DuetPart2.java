package nl.mvdr.adventofcode.adventofcode2017.day18;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nl.mvdr.adventofcode.solver.IntSolver;
import nl.mvdr.adventofcode.adventofcode2017.duet.Instruction;
import nl.mvdr.adventofcode.adventofcode2017.duet.Program;
import nl.mvdr.adventofcode.adventofcode2017.duet.SendInstruction;
import nl.mvdr.adventofcode.adventofcode2017.duet.State;

/**
 * Solution to the day 18 puzzle of 2017's Advent of Code:
 * <a href="https://adventofcode.com/2017/day/18">Duet</a>.
 *
 * @author Martijn van de Rijdt
 */
public class DuetPart2 implements IntSolver {

    private static final Logger LOGGER = LoggerFactory.getLogger(DuetPart2.class);
    
    /**
     * {@inheritDoc}
     * 
     * @return number of times program 1 sent a message
     */
    @Override
    public int solve(Stream<String> lines) {
        List<Instruction> instructions = Instruction.parseInstructions(lines, false);
        
        Queue<Long> queue0 = new LinkedList<>();
        Queue<Long> queue1 = new LinkedList<>();
        
        Program program0 = new Program(instructions, new State(0, queue0, queue1), "0");
        Program program1 = new Program(instructions, new State(1, queue1, queue0), "1");
        
        while (program0.canProceed() || program1.canProceed()) {
            program0 = program0.executeInstructions();
            program1 = program1.executeInstructions();
        }
        
        LOGGER.debug("End program 0: {}", program0);
        LOGGER.debug("End program 1: {}", program1);
        
        return program1.timesExecuted(SendInstruction.class);
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
