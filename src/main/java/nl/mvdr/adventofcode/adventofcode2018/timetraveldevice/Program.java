package nl.mvdr.adventofcode.adventofcode2018.timetraveldevice;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

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
     * Constructor.
     * 
     * @param instructions instructions to be executed
     */
    public Program(List<Instruction> instructions) {
        super();
        // Add a dummy fifth register to hold the instruction pointer.
        this.numberOfRegisters = 5;
        this.instructionPointerRegister = 4;
        this.instructions = instructions;
        
    }
    
    /**
     * Constructor.
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
        // Initialise the registers
        int[] registers = new int[numberOfRegisters];
        registers[0] = register0Value;
        
        // TODO day 21: infinite loop / livelock detection?
        while (0 <= registers[instructionPointerRegister] && registers[instructionPointerRegister] < instructions.size()) {
            Instruction instruction = instructions.get(registers[instructionPointerRegister]);
            registers = instruction.execute(registers);
            registers[instructionPointerRegister]++;
        }
        
        return registers[0];
    }
}
