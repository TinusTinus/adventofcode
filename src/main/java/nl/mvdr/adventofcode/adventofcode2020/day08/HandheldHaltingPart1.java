package nl.mvdr.adventofcode.adventofcode2020.day08;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nl.mvdr.adventofcode.solver.IntSolver;

/**
 * Solution to the day 8 puzzle of 2020's Advent of Code:
 * <a href="https://adventofcode.com/2020/day/8">Handheld Halting</a>.
 *
 * @author Martijn van de Rijdt
 */
public class HandheldHaltingPart1 implements IntSolver {

    private static final Logger LOGGER = LoggerFactory.getLogger(HandheldHaltingPart1.class);

    /**
     * {@inheritDoc}
     * 
     * @return value in the accumulator, immediately before any instruction is executed a second time
     */
    @Override
    public int solve(Stream<String> lines) {
        List<Instruction> instructions = Instruction.parse(lines);
        
        Set<Integer> executedInstructions = new HashSet<>();
        
        ProgramState state = ProgramState.INITIAL;
        
        ProgramState nextState = instructions.get(state.instructionPointer()).execute(state);
        
        while (!executedInstructions.contains(Integer.valueOf(nextState.instructionPointer()))) {
            executedInstructions.add(Integer.valueOf(nextState.instructionPointer()));
            state = nextState;
            nextState = instructions.get(state.instructionPointer()).execute(state);
        }
        
        return state.accumulator();
    }

    /**
     * Main method.
     * 
     * @param args commandline arguments; these are ignored
     */
    public static void main(String[] args) {
        HandheldHaltingPart1 instance = new HandheldHaltingPart1();

        String result = instance.solve("input-day08-2020.txt");

        LOGGER.info(result);
    }
}
 