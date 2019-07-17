package nl.mvdr.adventofcode.adventofcode2018.day21;

import java.io.IOException;
import java.nio.file.Path;
import java.util.stream.IntStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nl.mvdr.adventofcode.PathSolver;
import nl.mvdr.adventofcode.adventofcode2018.timetraveldevice.LiveLockException;
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
        
//        program.execute(0);
        
        IntStream.range(0, 10_000).forEach(i -> {
            try {
                int result = program.execute(i, 1_000);
                
                LOGGER.info("{}: {}", Integer.valueOf(i), Integer.valueOf(result));
            } catch (LiveLockException e) {
                // ignore
            }
        });
        
        return null; // TODO
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
