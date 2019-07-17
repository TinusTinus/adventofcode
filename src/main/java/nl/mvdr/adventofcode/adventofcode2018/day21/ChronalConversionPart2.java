package nl.mvdr.adventofcode.adventofcode2018.day21;

import java.io.IOException;
import java.nio.file.Path;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nl.mvdr.adventofcode.PathSolver;
import nl.mvdr.adventofcode.adventofcode2018.timetraveldevice.Program;
import nl.mvdr.adventofcode.adventofcode2018.timetraveldevice.ProgramExecutionCallback;

/**
 * Solution to the day 21 puzzle of 2018's Advent of Code:
 * <a href="https://adventofcode.com/2018/day/19">Go With The Flow</a>.
 *
 * @author Martijn van de Rijdt
 */
public class ChronalConversionPart2 implements PathSolver {

    private static final Logger LOGGER = LoggerFactory.getLogger(ChronalConversionPart2.class);

    private Set<Integer> candidates;
    
    private String result;
    
    /** Constructor. */
    public ChronalConversionPart2() {
        super();
        this.candidates = new HashSet<>();
    }
    
    @Override
    public String solve(Path inputFilePath) throws IOException {
        Program program = Program.parse(inputFilePath);
        program.execute(0, this::continueExecution);
        return result;
    }
    
    /**
     * Callback for {@link Program#execute(int, ProgramExecutionCallback)}.
     * 
     * @param registers current register values
     * @param instructionPointer current instruction pointer
     * @return whether to continue execution
     */
    private boolean continueExecution(List<Integer> registers, int instructionPointer) {
        boolean continueProgram;
        
        // By analyzing the program text:
        // the program will halt if, when executing the instruction on line 30, register 0 equals register 4.
        if (instructionPointer == 30) {
            LOGGER.debug("Candidate: {}", registers.get(4));
            boolean added = candidates.add(registers.get(4));
            
            if (added) {
                result = "" + registers.get(4);
            }
            // If a duplicate was found, there is no need to continue searching; the program will cycle.
            // Return the previously found value.
            continueProgram = added;
        } else {
            continueProgram = true;
        }
        return continueProgram;
    }
    
    /**
     * Main method.
     * 
     * @param args commandline arguments; these are ignored
     */
    public static void main(String[] args) {
        ChronalConversionPart2 solver = new ChronalConversionPart2();
        String solution = solver.solve("input-day21-2018.txt");
        LOGGER.info(solution);
    }
}
