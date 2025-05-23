package nl.mvdr.adventofcode.adventofcode2022.day10;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Result of running a program.
 *
 * @param signalStrengthSum the signal strength during the 20th cycle and every 40 cycles after that
 *          (that is, during the 20th, 60th, 100th, 140th, 180th, and 220th cycles)
 * @param image the image generated by the program
 *
 * @author Martijn van de Rijdt
 */
record ProgramResult(int signalStrengthSum, String image) {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(ProgramResult.class);
    
    /**
     * Parses and executes the given program.
     * 
     * @param lines puzzle input
     * @return result
     */
    static ProgramResult get(Stream<String> lines) {
        List<AtomicInstruction> program = parseProgram(lines.toList());
        return execute(program);
    }

    /**
     * Parses the puzzle input into a program.
     * 
     * @param linesStream puzzle input
     * @return program
     */
    private static List<AtomicInstruction> parseProgram(List<String> lines) {
        List<AtomicInstruction> program = new ArrayList<>();
        for (var line : lines) {
            if ("noop".equals(line)) {
                program.add(NoopInstruction.INSTANCE);
            } else if (line.startsWith("addx ")) {
                var value = Integer.parseInt(line.substring(5));
                // The addx instruction takes two cycles.
                // Model this by splitting it up into two single-cycle operations: noop and the actual add.
                program.add(NoopInstruction.INSTANCE);
                program.add(new AtomicAddXInstruction(value));
            } else {
                throw new IllegalArgumentException("Unable to parse line: " + line);
            }
        }
        return program;
    }
    
    /**
     * Executes the given program.
     * 
     * @param program list of instructions, where every instruction takes exactly one cycle
     * @return result of executing the given program
     */
    private static ProgramResult execute(List<AtomicInstruction> program) {
        var x = 1;
        var cycle = 0;
        var signalStrengthSum = 0;
        var image = new StringBuilder();
        var row = new StringBuilder();
        
        for (AtomicInstruction instruction : program) {
            cycle++;
            
            LOGGER.debug("Start cycle {}: begin executing {}", Integer.valueOf(cycle), instruction);
            
            if (cycle % 40 == 20) {
                int signalStrength = cycle * x;
                LOGGER.debug("During cycle {}: signal strength = {}", Integer.valueOf(cycle), Integer.valueOf(signalStrength));
                signalStrengthSum = signalStrengthSum + signalStrength;
            }

            LOGGER.debug("During cycle {}: CRT draws pixel in position {}", Integer.valueOf(cycle), Integer.valueOf(row.length()));
            if (Math.abs(x - row.length()) <= 1) {
                row.append("#");
            } else {
                row.append(".");
            }
            LOGGER.debug("Current CRT row: {}" , row);
            
            if (row.length() == 40) {
                // Row is complete
                image.append(row);
                image.append("\n");
                // Start a new row
                row = new StringBuilder();
            }
            
            x = instruction.perform(x);
            LOGGER.debug("Finish executing atomic instruction {} (Register is now {})", instruction, Integer.valueOf(x));
        }
        
        return new ProgramResult(signalStrengthSum, image.toString());
    }
}
