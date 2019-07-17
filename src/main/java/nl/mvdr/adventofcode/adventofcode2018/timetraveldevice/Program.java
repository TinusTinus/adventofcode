package nl.mvdr.adventofcode.adventofcode2018.timetraveldevice;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A program for the time travel device, used in multiple puzzles.
 *
 * @author Martijn van de Rijdt
 */
public class Program {
    
    private final Logger LOGGER = LoggerFactory.getLogger(Program.class);
    
    /** The number of registers. */
    private final int numberOfRegisters;
    
    /** The index of the register bound to the instruction pointer. */
    private final int instructionPointerRegister;
    
    /** The list of instructions forming this program. */
    private final List<Instruction> instructions;
    
    private final Set<List<Integer>> states;

    /**
     * Parses the given input file into a program.
     * 
     * @param inputFilePath path to the input text file
     * @return program
     * @throws IOException in case the file could not be read
     */
    public static Program parse(Path inputFilePath) throws IOException {
        List<String> lines = Files.lines(inputFilePath)
                // ignore empty lines (the last line in the file)
                .filter(Objects::nonNull)
                .filter(line -> !line.isBlank())
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
        
        this.states = new HashSet<>();
        
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
        
        this.states = new HashSet<>();
    }

    /**
     * Executes this program.
     * 
     * @param register0Value the initial value of register 0; all other registers are initialized as 0
     * @return value of register 0
     */
    public int execute(int register0Value) {
        // Use Integer.MAX_VALUE as a reasonable approximation of infinity
        return execute(register0Value, Integer.MAX_VALUE);
    }
    
    /**
     * Executes this program.
     * 
     * @param register0Value the initial value of register 0; all other registers are initialized as 0
     * @param maximumNumberOfInstructions the maximum number of instructions to perform
     * @return value of register 0
     */
    public int execute(int register0Value, int maximumNumberOfInstructions) {
        // Initialise the registers
        List<Integer> registers = new ArrayList<>(Collections.nCopies(numberOfRegisters, 0));
        registers.set(0, register0Value);
        
        int instructionsPerformed = 0;
        
        while (0 <= registers.get(instructionPointerRegister).intValue()
                && registers.get(instructionPointerRegister).intValue() < instructions.size()
                && instructionsPerformed <= maximumNumberOfInstructions) {
            Instruction instruction = instructions.get(registers.get(instructionPointerRegister).intValue());
            registers = instruction.execute(registers);
            registers.set(instructionPointerRegister, Integer.valueOf(registers.get(instructionPointerRegister).intValue() + 1));
            instructionsPerformed++;
            
            boolean added = states.add(registers);
            if (!added) {
                LOGGER.info("Register state encountered previously: {}", registers);
            }
        }
        
        if (maximumNumberOfInstructions < instructionsPerformed) {
            throw new LiveLockException("Program has not halted after " + maximumNumberOfInstructions + " instructions");
        }
        
        return registers.get(0);
    }
}
