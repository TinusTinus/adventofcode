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
public class CathodeRayTube implements IntSolver {

    private static final Logger LOGGER = LoggerFactory.getLogger(CathodeRayTube.class);
    
    @Override
    public int solve(Stream<String> linesStream) {
        List<AtomicInstruction> program = parseProgram(linesStream);
        
        var x = 1;
        var cycle = 0;
        var result = 0;
        var image = new StringBuilder();
        
        while(cycle < program.size()) {
            cycle++;
            
            int cycleMod40 = cycle % 40;
            
            if (cycleMod40 == 20) {
                int signalStrength = cycle * x;
                LOGGER.debug("Signal strength during cycle {}: {}", Integer.valueOf(cycle), Integer.valueOf(signalStrength));
                result = result + signalStrength;
            }

            if (Math.abs(x - cycleMod40) <= 1) {
                image.append("#");
            } else {
                image.append(".");
            }
            if (cycleMod40 == 0) {
                image.append("\n");
            }
            
            var instruction = program.get(cycle - 1); // off by 1
            x = instruction.perform(x);
            LOGGER.debug("x = {} after cycle {}, last atomic instruction was: {}", Integer.valueOf(x), Integer.valueOf(cycle), instruction);
        }
        
        LOGGER.info("Image:\n{}", image);
        
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
        var instance = new CathodeRayTube();

        var result = instance.solve("input-day10-2022.txt");

        LOGGER.info(result);
    }
}
 