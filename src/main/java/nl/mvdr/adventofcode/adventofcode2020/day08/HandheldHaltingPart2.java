package nl.mvdr.adventofcode.adventofcode2020.day08;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.Set;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nl.mvdr.adventofcode.IntSolver;

/**
 * Solution to the day 8 puzzle of 2020's Advent of Code:
 * <a href="https://adventofcode.com/2020/day/8">Handheld Halting</a>.
 *
 * @author Martijn van de Rijdt
 */
public class HandheldHaltingPart2 implements IntSolver {

    private static final Logger LOGGER = LoggerFactory.getLogger(HandheldHaltingPart2.class);

    /**
     * {@inheritDoc}
     * 
     * @return value in the accumulator, immediately before any instruction is executed a second time
     */
    @Override
    public int solve(Stream<String> lines) {
        List<Instruction> instructions = Instruction.parse(lines);
        
        return IntStream.range(0, instructions.size())
                .mapToObj(index -> updateInstructionAt(index, instructions))
                .filter(Optional::isPresent)
                .map(Optional::orElseThrow)
                .map(this::execute)
                .filter(OptionalInt::isPresent)
                .mapToInt(OptionalInt::orElseThrow)
                .findAny()
                .orElseThrow();
    }
    
    /**
     * Updates the instruction at the given index.
     * 
     * If the instruction at the given index is a {@link Operation#JMP}, it is replaced by a {@link Operation#NOP}.
     * If it is a {@link Operation#NOP}, it is replaced by a {@link Operation#JMP}.
     * Otherwise (if it is an {@link Operation#ACC}), this method returns an empty value.
     * 
     * @param index instruction index, must be a valid index in the given list of instructions
     * @param instructions list of instructions
     * @return copy of the list of instructions with the updated instruction, or an empty value if not applicable
     */
    private Optional<List<Instruction>> updateInstructionAt(int index, List<Instruction> instructions) {
        Optional<List<Instruction>> result;
        Instruction instruction = instructions.get(index);
        if (instruction.operation() == Operation.NOP) {
            List<Instruction> copy = new ArrayList<>(instructions);
            copy.set(index, new Instruction(Operation.JMP, instruction.argument()));
            result = Optional.of(copy);
        } else if (instruction.operation() == Operation.JMP) {
            List<Instruction> copy = new ArrayList<>(instructions);
            copy.set(index, new Instruction(Operation.NOP, instruction.argument()));
            result = Optional.of(copy);
        } else {
            result = Optional.empty();
        }
        return result;
    }
    
    /**
     * Executes the given program, until either it terminates or an infinite loop is detected..
     * 
     * @param instructions instructions to execute
     * @return in case the program terminates normally: the value of the accumulator; otherwise: empty
     */
    private OptionalInt execute(List<Instruction> instructions) {
        Set<Integer> executedInstructions = new HashSet<>();
        
        ProgramState state = ProgramState.initialState();
        
        ProgramState nextState = instructions.get(state.instructionPointer()).execute(state);
        
        while (0 <= nextState.instructionPointer() && nextState.instructionPointer() < instructions.size()
                && !executedInstructions.contains(Integer.valueOf(nextState.instructionPointer()))) {
            executedInstructions.add(Integer.valueOf(nextState.instructionPointer()));
            state = nextState;
            nextState = instructions.get(state.instructionPointer()).execute(state);
        }
        
        
        OptionalInt result;
        if (nextState.instructionPointer() == instructions.size()) {
            LOGGER.debug("The program completed successfully: {} - {}", nextState, instructions);
            result = OptionalInt.of(nextState.accumulator());
        } else {
            LOGGER.debug("Either an infinite loop was detected, or the instruction pointer jumped too far out of bounds: {} - {}", nextState, instructions);
            result = OptionalInt.empty();
        }
        
        return result;
    }

    /**
     * Main method.
     * 
     * @param args commandline arguments; these are ignored
     */
    public static void main(String[] args) {
        HandheldHaltingPart2 instance = new HandheldHaltingPart2();

        String result = instance.solve("input-day08-2020.txt");

        LOGGER.info(result);
    }
}
 