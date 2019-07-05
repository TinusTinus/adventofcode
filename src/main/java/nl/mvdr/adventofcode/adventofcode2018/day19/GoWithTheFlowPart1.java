package nl.mvdr.adventofcode.adventofcode2018.day19;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nl.mvdr.adventofcode.PathSolver;

/**
 * Solution to the day 16 puzzle of 2018's Advent of Code:
 * <a href="https://adventofcode.com/2018/day/19">Go With The Flow</a>.
 *
 * @author Martijn van de Rijdt
 */
public class GoWithTheFlowPart1 implements PathSolver {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(GoWithTheFlowPart1.class);
    
    @Override
    public String solve(Path inputFilePath) throws IOException {
        List<String> lines = Files.lines(inputFilePath)
                // ignore empty lines (the last line in the file)
                .filter(Objects::nonNull)
                .filter(line -> !line.isBlank())
                .collect(Collectors.toList());
        
        // The first line indicates the initial value for the instruction pointer binding.
        // For example: "#ip 0".
        int instructionPointerRegister = Integer.parseInt(lines.get(0).substring(4, 5));

        List<Instruction> instructions = lines.subList(1, lines.size()).stream()
            .map(Instruction::parse)
            .collect(Collectors.toList());
        
        int[] registers = { 0, 0, 0, 0, 0, 0 };
        
        while (0 <= registers[instructionPointerRegister] && registers[instructionPointerRegister] < instructions.size()) {
            Instruction instruction = instructions.get(registers[instructionPointerRegister]);
            registers = instruction.execute(registers);
            registers[instructionPointerRegister]++;
        }
        
        return "" + registers[0];
    }
    
    /**
     * Main method.
     * 
     * @param args commandline arguments; these are ignored
     */
    public static void main(String[] args) {
        GoWithTheFlowPart1 solver = new GoWithTheFlowPart1();
        String solution = solver.solve("input-day19-2018.txt");
        LOGGER.info(solution);
    }
}
