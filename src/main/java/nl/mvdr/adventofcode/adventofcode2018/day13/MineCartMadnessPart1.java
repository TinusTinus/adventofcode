package nl.mvdr.adventofcode.adventofcode2018.day13;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nl.mvdr.adventofcode.point.Point;

/**
 * Solution to part 1 of the puzzle.
 * 
 * @author Martijn van de Rijdt
 */
public class MineCartMadnessPart1 extends MineCartMadness {

    private static final Logger LOGGER = LoggerFactory.getLogger(MineCartMadnessPart1.class);

    @Override
    protected Point solve(State initialState) {
        Point result;
        State state = initialState;
        try {
            while (true) {
                state = state.tick(true);
            }
        } catch (CollisionException e) {
            result = e.getLocation();
        }

        return result;
    }

    /**
     * Main method.
     * 
     * @param args commandline arguments; these are ignored
     */
    public static void main(String[] args) {
        MineCartMadnessPart1 solver = new MineCartMadnessPart1();
        String solution = solver.solve("input-day13-2018.txt");
        LOGGER.info(solution);
    }
}
