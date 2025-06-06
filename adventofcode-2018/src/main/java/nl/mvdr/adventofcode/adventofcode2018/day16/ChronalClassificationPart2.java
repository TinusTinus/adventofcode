package nl.mvdr.adventofcode.adventofcode2018.day16;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nl.mvdr.adventofcode.solver.IntSolver;
import nl.mvdr.adventofcode.adventofcode2018.timetraveldevice.Instruction;
import nl.mvdr.adventofcode.adventofcode2018.timetraveldevice.Opcode;
import nl.mvdr.adventofcode.adventofcode2018.timetraveldevice.Program;

/**
 * Solution to the day 16 puzzle of 2018's Advent of Code:
 * <a href="https://adventofcode.com/2018/day/16">Chronal Classification</a>.
 *
 * @author Martijn van de Rijdt
 */
public class ChronalClassificationPart2 implements IntSolver {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(ChronalClassificationPart2.class);
    
    @Override
    public int solve(Stream<String> lines) {
        PuzzleInput input = PuzzleInput.parse(lines);
        
        // Build up a map of opcode number to opcode.
        Map<Integer, Opcode> opcodeMapping = new HashMap<>();
        while (opcodeMapping.size() < Opcode.values().length) {
            // Not all opcodes have been figured out yet.
            for (Sample sample : input.getSamples()) {
                Set<Opcode> potentialOpcodes = sample.getMatchingOpcodes().stream()
                        .filter(opcode -> !opcodeMapping.values().contains(opcode))
                        .collect(Collectors.toSet());
                if (potentialOpcodes.size() == 1) {
                    opcodeMapping.put(Integer.valueOf(sample.getInstruction().getOpcodeNumber()), potentialOpcodes.iterator().next());
                }
            }
        }

        List<Instruction> instructions = input.getInstructions().stream()
                .map(instruction -> instruction.toInstruction(opcodeMapping::get))
                .collect(Collectors.toList());
        
        Program program = new Program(instructions);
        
        return program.execute(0);
    }
    
    /**
     * Main method.
     * 
     * @param args commandline arguments; these are ignored
     */
    public static void main(String[] args) {
        ChronalClassificationPart2 solver = new ChronalClassificationPart2();
        String solution = solver.solve("input-day16-2018.txt");
        LOGGER.info(solution);
    }
}
