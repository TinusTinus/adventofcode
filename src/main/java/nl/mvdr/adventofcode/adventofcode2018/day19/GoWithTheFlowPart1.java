package nl.mvdr.adventofcode.adventofcode2018.day19;

import java.io.IOException;
import java.nio.file.Path;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nl.mvdr.adventofcode.PathSolver;
import nl.mvdr.adventofcode.adventofcode2018.timetraveldevice.Program;

/**
 * Solution to the day 19 puzzle of 2018's Advent of Code:
 * <a href="https://adventofcode.com/2018/day/19">Go With The Flow</a>.
 *
 * @author Martijn van de Rijdt
 */
public class GoWithTheFlowPart1 implements PathSolver<Integer> {

    private static final Logger LOGGER = LoggerFactory.getLogger(GoWithTheFlowPart1.class);

    @Override
    public Integer solve(Path inputFilePath) throws IOException {
        Program program = Program.parse(inputFilePath);
        
        return Integer.valueOf(program.execute(0));
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
