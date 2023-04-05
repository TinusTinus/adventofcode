package nl.mvdr.adventofcode.adventofcode2022.day10;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nl.mvdr.adventofcode.IntSolver;

/**
 * Solution to <a href="https://adventofcode.com/2022/day/10">Cathode-Ray Tube</a>.
 *
 * @author Martijn van de Rijdt
 */
public class CathodeRayTubePart1 implements IntSolver {

    private static final Logger LOGGER = LoggerFactory.getLogger(CathodeRayTubePart1.class);
    
    @Override
    public int solve(Stream<String> linesStream) {
        List<AtomicInstruction> program = parseProgram(linesStream);
        
        var x = 1;
        var cycle = 0;
        var result = 0;
        
        while(cycle <= 220) {
            cycle++;
            
            if (cycle % 40 == 20) {
                int signalStrength = cycle * x;
                LOGGER.debug("Signal strength during cycle {}: {}", Integer.valueOf(cycle), Integer.valueOf(signalStrength));
                result = result + signalStrength;
            }
            
            var instruction = program.get(cycle - 1); // off by 1
            x = instruction.perform(x);
            LOGGER.debug("x = {} after cycle {}, last atomic instruction was: {}", Integer.valueOf(x), Integer.valueOf(cycle), instruction);
        }
        
        return result;
    }

    /**
     * Parses the puzzle input into a program.
     * 
     * @param linesStream puzzle input
     * @return program
     */
    private static List<AtomicInstruction> parseProgram(Stream<String> linesStream) {
        var lines = linesStream.toList();
        
        List<AtomicInstruction> program = new ArrayList<>();
        for (var line : lines) {
            if ("noop".equals(line)) {
                program.add(NoopInstruction.INSTANCE);
            } else if (line.startsWith("addx ")) {
                var value = Integer.parseInt(line.substring(5));
                program.add(NoopInstruction.INSTANCE);
                program.add(new AtomicAddXInstruction(value));
            } else {
                throw new IllegalArgumentException("Unable to parse line: " + line);
            }
        }
        return program;
    }
    
    /**
     * Main method.
     * 
     * @param args commandline arguments; these are ignored
     */
    public static void main(String[] args) {
        var instance = new CathodeRayTubePart1();

        var result = instance.solve("input-day10-2022.txt");

        LOGGER.info(result);
    }
}
 