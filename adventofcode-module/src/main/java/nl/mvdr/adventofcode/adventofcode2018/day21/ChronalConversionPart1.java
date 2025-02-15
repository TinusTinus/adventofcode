package nl.mvdr.adventofcode.adventofcode2018.day21;

import java.util.List;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nl.mvdr.adventofcode.solver.IntSolver;
import nl.mvdr.adventofcode.adventofcode2018.timetraveldevice.Program;
import nl.mvdr.adventofcode.adventofcode2018.timetraveldevice.ProgramExecutionCallback;

/**
 * Solution to the day 21 puzzle of 2018's Advent of Code:
 * <a href="https://adventofcode.com/2018/day/21">Chronal Conversion</a>.
 *
 * @author Martijn van de Rijdt
 */
public class ChronalConversionPart1 implements IntSolver {

    private static final Logger LOGGER = LoggerFactory.getLogger(ChronalConversionPart1.class);

    private Integer result;
    
    @Override
    public int solve(Stream<String> lines) {
        Program program = Program.parse(lines);
        program.execute(0, this::continueExecution);
        return result.intValue();
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
            result = registers.get(4);
            // Answer found, no need to continue execution.
            // (In fact, the program may never halt.)
            continueProgram = false;
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
        ChronalConversionPart1 solver = new ChronalConversionPart1();
        String solution = solver.solve("input-day21-2018.txt");
        LOGGER.info(solution);
    }
}
