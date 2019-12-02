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
            int opcode = result.memory.get(result.instructionPointer).intValue();
            Instruction instruction = Instruction.of(opcode);
            LOGGER.debug("Executing instruction {}", instruction);
            result = instruction.execute(result);
            LOGGER.debug("Updated program state: {}", result);
        }
        return result;
    }
    
    /** @return updated program state after halting */
    Program halt() {
        return new Program(memory, instructionPointer, true);
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
        LOGGER.debug("Performing {} {} {} {}",
                memory.get(instructionPointer),
                memory.get(instructionPointer + 1),
                memory.get(instructionPointer + 2),
                memory.get(instructionPointer + 3));
        
        int address1 = memory.get(instructionPointer + 1).intValue();
        int address2 = memory.get(instructionPointer + 2).intValue();
        int address3 = memory.get(instructionPointer + 3).intValue();
        
        int value1 = memory.get(address1).intValue();
        int value2 = memory.get(address2).intValue();
        LOGGER.debug("memory[{}] = {}", Integer.valueOf(address1), Integer.valueOf(value1));
        LOGGER.debug("memory[{}] = {}", Integer.valueOf(address2), Integer.valueOf(value2));
        
        int value3 = operator.applyAsInt(value1, value2);
        
        LOGGER.debug("{} op {} = {}", Integer.valueOf(value1), Integer.valueOf(value2), Integer.valueOf(value3));
        
        List<Integer> newMemory = new ArrayList<>(memory);
        LOGGER.debug("Writing value {} to address {}", Integer.valueOf(value3), Integer.valueOf(address3));
        newMemory.set(address3, Integer.valueOf(value3));
        
        return new Program(List.copyOf(newMemory), instructionPointer + 4, false);
    }
    
    public List<Integer> getMemory() {
        return memory;
    }
    
    @Override
    public String toString() {
        return "Program [memory=" + memory + ", instructionPointer=" + instructionPointer + ", done=" + done + "]";
    }
}
