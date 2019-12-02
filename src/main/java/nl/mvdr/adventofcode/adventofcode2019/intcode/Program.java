package nl.mvdr.adventofcode.adventofcode2019.intcode;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Representation of an Intcode program, including its current state.
 *
 * @author Martijn van de Rijdt
 */
public class Program {
    
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
        return this; // TODO
    }
    
    public List<Integer> getIntegers() {
        return integers;
    }
}
