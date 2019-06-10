package nl.mvdr.adventofcode.adventofcode2018.day13;

import java.io.IOException;
import java.nio.file.Path;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nl.mvdr.adventofcode.PathSolver;

/**
 * Solution to the day 13 puzzle of 2018's Advent of Code:
 * <a href="https://adventofcode.com/2018/day/13">Mine Cart Madness</a>.
 *
 * Soundtrack for this puzzle:
 * <a href="https://www.youtube.com/watch?v=PVPqxuebYQw">Mine Cart Madness</a>
 * by <a href="https://en.wikipedia.org/wiki/David_Wise_(composer)">David
 * Wise</a>.
 * 
 * @author Martijn van de Rijdt
 */
public class MineCartMadnessPart2 implements PathSolver {

    private static final Logger LOGGER = LoggerFactory.getLogger(MineCartMadnessPart2.class);

    @Override
    public String solve(Path inputFilePath) throws IOException {
        State state = State.parse(inputFilePath);

        while (1 < state.getCarts().size()) {
            state = state.tick(false);
        }

        MineCart remainingCart = state.getCarts().iterator().next();
        
        return remainingCart.getX() + "," + remainingCart.getY();
    }

    /**
     * Main method.
     * 
     * @param args commandline arguments; these are ignored
     */
    public static void main(String[] args) {
        MineCartMadnessPart2 solver = new MineCartMadnessPart2();
        String solution = solver.solve("input-day13-2018.txt");
        LOGGER.info(solution);
    }
}
