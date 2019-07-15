package nl.mvdr.adventofcode.adventofcode2018.day19;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nl.mvdr.adventofcode.PathSolver;
import nl.mvdr.adventofcode.adventofcode2018.opcode.Instruction;

/**
 * Solution to the day 19 puzzle of 2018's Advent of Code:
 * <a href="https://adventofcode.com/2018/day/19">Go With The Flow</a>.
 *
 * @author Martijn van de Rijdt
 */
abstract class GoWithTheFlow implements PathSolver {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(GoWithTheFlow.class);
    
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
        // The rest are instructions.
        List<Instruction> instructions = Instruction.parse(lines.subList(1, lines.size()));
        
        int[] registers = getInitialRegisters();
        long executed = 0L;
        while (0 <= registers[instructionPointerRegister] && registers[instructionPointerRegister] < instructions.size()) {
            Instruction instruction = instructions.get(registers[instructionPointerRegister]);
            registers = instruction.execute(registers);
            registers[instructionPointerRegister]++;
            
            executed++;
            if (executed % 10_000_000L == 0) {
                LOGGER.info(Arrays.toString(registers));
            }
        }
        
        return "" + registers[0];
    }
    
    /** @return initial value for the registers */
    protected abstract int[] getInitialRegisters();
}
