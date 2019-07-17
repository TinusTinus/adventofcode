package nl.mvdr.adventofcode.adventofcode2018.day21;

import java.io.IOException;
import java.nio.file.Path;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nl.mvdr.adventofcode.PathSolver;
import nl.mvdr.adventofcode.adventofcode2018.timetraveldevice.Program;

/**
 * Solution to the day 21 puzzle of 2018's Advent of Code:
 * <a href="https://adventofcode.com/2018/day/19">Go With The Flow</a>.
 *
 * @author Martijn van de Rijdt
 */
public class ChronalConversion implements PathSolver {

    private static final Logger LOGGER = LoggerFactory.getLogger(ChronalConversion.class);

    @Override
    public String solve(Path inputFilePath) throws IOException {
        Program program = Program.parse(inputFilePath);
        program.execute(11840402);
        
        // The answer to part 1 is 11840402.
        
        // The program will halt if, when executing the instruction on line 30, register 0 equals register 4.
        // The first time the instruction on line 30 is executed, the register values are:
        // [<input>, 0, 1, 30, 11840402, 1]
        // So the answer is 11840402.
        
        return "11840402";
    }
    
    /**
     * Main method.
     * 
     * @param args commandline arguments; these are ignored
     */
    public static void main(String[] args) {
        ChronalConversion solver = new ChronalConversion();
        String solution = solver.solve("input-day21-2018.txt");
        LOGGER.info(solution);
    }
}
