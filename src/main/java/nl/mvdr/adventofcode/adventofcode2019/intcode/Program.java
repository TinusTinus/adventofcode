package nl.mvdr.adventofcode.adventofcode2019.intcode;

import java.util.List;
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
        LOGGER.info("Initial program state: {}", this); // TODO debug
        Program result = this;
        while (!result.done) {
            int opcode = result.integers.get(result.instructionPointer).intValue();
            Instruction instruction = Instruction.of(opcode);
            LOGGER.info("Executing instruction {}", instruction); // TODO debug
            result = instruction.execute(result);
            LOGGER.info("Updated program state: {}", result); // TODO debug
        }
        return result;
    }
    
    public List<Integer> getIntegers() {
        return integers;
    }
    
    int getInstructionPointer() {
        return instructionPointer;
    }

    @Override
    public String toString() {
        return "Program [integers=" + integers + ", instructionPointer=" + instructionPointer + ", done=" + done + "]";
    }
    
}
