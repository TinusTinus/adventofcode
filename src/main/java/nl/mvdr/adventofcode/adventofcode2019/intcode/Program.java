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
    
    private final List<Integer> memory;
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
     * @param memory list of values making up this program and its memory
     * @param instructionPointer current value of the instruction pointer
     * @param done whether program execution has halted
     */
    Program(List<Integer> memory, int instructionPointer, boolean done) {
        super();
        this.memory = memory;
        this.instructionPointer = instructionPointer;
        this.done = done;
    }

    /**
     * Updates the value at the given index.
     * 
     * @param address memory index to update
     * @param value value to write to index
     * @return copy of this program with the updated value
     */
    public Program set(int address, int value) {
        List<Integer> newIntegers = new ArrayList<>(memory);
        newIntegers.set(address, Integer.valueOf(value));
        return new Program(List.copyOf(newIntegers), instructionPointer, done);
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
            int instructionPointerValue = result.memory.get(result.instructionPointer).intValue();
            
            int opcode = instructionPointerValue % 100;
            Instruction instruction = Instruction.of(opcode);
            
            instructionPointerValue = instructionPointerValue / 100;
            List<ParameterMode> parameterModes = new ArrayList<>(instruction.getParameterCount());
            for (int i = 0; i != instruction.getParameterCount(); i++) {
                parameterModes.add(ParameterMode.of(instructionPointerValue % 10));
                instructionPointerValue = instructionPointerValue / 10;
            }
            
            LOGGER.debug("Executing instruction {} {}", instruction, parameterModes);
            result = instruction.execute(result, parameterModes);
            LOGGER.debug("Updated program state: {}", result);
        }
        return result;
    }
    
    /** @return updated program state after halting */
    Program halt(List<ParameterMode> parameterModes) {
        // validate modes
        if (!parameterModes.isEmpty()) {
            throw new IllegalArgumentException("No parameters expected, got: " + parameterModes);
        }
        
        return new Program(memory, instructionPointer, true);
    }
    
    /** 
     * Performs addition.
     * 
     * @param parameterModes parameter modes
     * @return updated program state 
     */
    Program add(List<ParameterMode> parameterModes) {
        return perform((i, j) -> i + j, parameterModes);
    }
    
    /** 
     * Performs multiplication.
     * 
     * @param parameterModes parameter modes
     * @return updated program state 
     */
    Program multiply(List<ParameterMode> parameterModes) {
        return perform((i, j) -> i * j, parameterModes);
    }
    
    /**
     * Performs a binary integer operation.
     * 
     * @param operator operator to perform
     * @param parameterModes parameter modes
     * @return updated program state after performing the given operator
     */
    private Program perform(IntBinaryOperator operator, List<ParameterMode> parameterModes) {
        // validate modes
        if (parameterModes.size() != 3) {
            throw new IllegalArgumentException("Unexpected number of paramters: " + parameterModes);
        }
        if (parameterModes.get(2) != ParameterMode.POSITION) {
            throw new IllegalArgumentException("Unexpected parameter mode for third parameter: " + parameterModes.get(2));
        }
        
        LOGGER.debug("Performing {} {} ({}) {} ({}) {} ({})",
                memory.get(instructionPointer),
                memory.get(instructionPointer + 1),
                parameterModes.get(0),
                memory.get(instructionPointer + 2),
                parameterModes.get(1),
                memory.get(instructionPointer + 3),
                parameterModes.get(2));
        
        int value0 = getValue(instructionPointer + 1, parameterModes.get(0));
        int value1 = getValue(instructionPointer + 2, parameterModes.get(1));
        
        int resultValue = operator.applyAsInt(value0, value1);
        
        int resultAddress = memory.get(instructionPointer + 3).intValue();
        
        List<Integer> newMemory = new ArrayList<>(memory);
        LOGGER.debug("Writing value {} to address {}", Integer.valueOf(resultValue), Integer.valueOf(resultAddress));
        newMemory.set(resultAddress, Integer.valueOf(resultValue));
        
        return new Program(List.copyOf(newMemory), instructionPointer + 4, false);
    }
    
    /**
     * Reads a parameter value from memory.
     * 
     * @param address address from which to retrieve a parameter value
     * @param mode parameter mode
     * @return value
     */
    private int getValue(int address, ParameterMode mode) {
        int value = memory.get(address).intValue();
        return switch (mode) {
            case IMMEDIATE -> value;
            case POSITION -> memory.get(value).intValue();
            default -> throw new IllegalArgumentException("Unexpected mode: " + mode);
        };
    }
    
    public List<Integer> getMemory() {
        return memory;
    }
    
    @Override
    public String toString() {
        return "Program [memory=" + memory + ", instructionPointer=" + instructionPointer + ", done=" + done + "]";
    }
}
