package nl.mvdr.adventofcode.adventofcode2018.timetraveldevice;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * A program for the time travel device, used in multiple puzzles.
 *
 * @author Martijn van de Rijdt
 */
public class Program {
    
    /** The number of registers. */
    private final int numberOfRegisters;
    
    /** The index of the register bound to the instruction pointer. */
    private final int instructionPointerRegister;
    
    /** The list of instructions forming this program. */
    private final List<Instruction> instructions;
    
    /**
     * Parses the given input file into a program.
     * 
     * @param linesStream contents of the input file
     * @return program
     */
    public static Program parse(Stream<String> linesStream) {
        List<String> lines = linesStream.filter(Predicate.not(String::isBlank))
                .collect(Collectors.toList());
        
        // The first line indicates the initial value for the instruction pointer binding.
        // For example: "#ip 0".
        int instructionPointerRegister = Integer.parseInt(lines.get(0).substring(4, 5));
        // The rest are instructions.
        List<Instruction> instructions = Instruction.parse(lines.subList(1, lines.size()));
        
        return new Program(instructionPointerRegister, instructions);
    }
    
    /**
     * Constructor for a basic program, with a simple list of instructions to be executed sequentially.
     * 
     * @param instructions instructions to be executed
     */
    public Program(List<Instruction> instructions) {
        super();
        
        // Four actual registers.
        // Add a dummy fifth register, to hold the instruction pointer.
        this.numberOfRegisters = 5;
        this.instructionPointerRegister = 4;
        
        this.instructions = instructions;
    }
    
    /**
     * Constructor for a program, where one of its registers is bound to the instruction pointer.
     * 
     * @param the register bound to the instruction pointer
     * @param instructions instructions to be executed
     */
    private Program(int instructionPointerRegister, List<Instruction> instructions) {
        super();
        this.numberOfRegisters = 6;
        this.instructionPointerRegister = instructionPointerRegister;
        this.instructions = instructions;
    }

    /**
     * Executes this program.
     * 
     * @param register0Value the initial value of register 0; all other registers are initialized as 0
     * @return value of register 0
     */
    public int execute(int register0Value) {
        return this.execute(register0Value, (registers, pointer) -> true);
    }
    
    /**
     * Executes this program.
     * 
     * @param register0Value the initial value of register 0; all other registers are initialized as 0
     * @param callback callback function
     * @return value of register 0
     */
    public int execute(int register0Value, ProgramExecutionCallback callback) {
        // Initialise the registers
        List<Integer> registers = new ArrayList<>(Collections.nCopies(numberOfRegisters, Integer.valueOf(0)));
        registers.set(0, Integer.valueOf(register0Value));
        
        while (callback.continueExecution(registers, registers.get(instructionPointerRegister).intValue())
                && 0 <= registers.get(instructionPointerRegister).intValue()
                && registers.get(instructionPointerRegister).intValue() < instructions.size()) {
            
            // Execute the next instruction
            Instruction instruction = instructions.get(registers.get(instructionPointerRegister).intValue());
            registers = instruction.execute(registers);
            
            // Increase the instruction pointer
            registers.set(instructionPointerRegister, Integer.valueOf(registers.get(instructionPointerRegister).intValue() + 1));
        }
        
        return registers.get(0).intValue();
    }
}
