package nl.mvdr.adventofcode.adventofcode2019.intcode;

import java.util.ArrayList;
import java.util.List;
import java.util.function.IntBinaryOperator;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Representation of an Intcode program, including its current state.
 *
 * @author Martijn van de Rijdt
 */
public class Program {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(Program.class);
    
    private final List<Integer> integers;
    private final int instructionPointer;
    private final boolean done;
    
    /**
     * Parses a textual representation of a program.
     * 
     * @param programText program text: a comma-separated list of integers
     * @return program
     */
    public static Program parse(String programText) {
        List<Integer> integers = Stream.of(programText.split(","))
                .map(Integer::valueOf)
                .collect(Collectors.toList());
        
        return new Program(List.copyOf(integers), 0, false);
    }
    
    /**
     * Constructor.
     * 
     * @param integers list of integers making up this program
     * @param instructionPointer current value of the instruction pointer
     * @param done whether program execution has halted
     */
    Program(List<Integer> integers, int instructionPointer, boolean done) {
        super();
        this.integers = integers;
        this.instructionPointer = instructionPointer;
        this.done = done;
    }
    
    /**
     * Executes this program.
     * 
     * @return program state after termination
     */
    public Program execute() {
        LOGGER.debug("Initial program state: {}", this);
        Program result = this;
        while (!result.done) {
            int opcode = result.integers.get(result.instructionPointer).intValue();
            Instruction instruction = Instruction.of(opcode);
            LOGGER.debug("Executing instruction {}", instruction);
            result = instruction.execute(result);
            LOGGER.debug("Updated program state: {}", result);
        }
        return result;
    }
    
    /** @return updated program state after halting */
    Program halt() {
        return new Program(integers, instructionPointer, true);
    }
    
    /** @return updated program state after adding */
    Program add() {
        return perform((i, j) -> i + j);
    }
    
    /** @return updated program state after multiplying */
    Program multiply() {
        return perform((i, j) -> i * j);
    }
    
    /**
     * Performs a binary integer operation.
     * 
     * @param operator operator to perform
     * @return updated program state after performing the given operator
     */
    private Program perform(IntBinaryOperator operator) {
        int index1 = integers.get(instructionPointer + 1).intValue();
        int index2 = integers.get(instructionPointer + 2).intValue();
        int index3 = integers.get(instructionPointer + 3).intValue();
        
        int value1 = integers.get(index1).intValue();
        int value2 = integers.get(index2).intValue();
        
        int value3 = operator.applyAsInt(value1, value2);
        
        LOGGER.debug("{} op {} = {}", Integer.valueOf(value1), Integer.valueOf(value2), Integer.valueOf(value3));
        
        List<Integer> newIntegers = new ArrayList<>(integers);
        newIntegers.set(index3, Integer.valueOf(value3));
        
        return new Program(List.copyOf(newIntegers), instructionPointer + 4, false);
    }
    
    public List<Integer> getIntegers() {
        return integers;
    }
    
    @Override
    public String toString() {
        return "Program [integers=" + integers + ", instructionPointer=" + instructionPointer + ", done=" + done + "]";
    }
}
