package nl.mvdr.adventofcode.adventofcode2018.day13;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nl.mvdr.adventofcode.point.Point;

/**
 * Solution to part 2 of the puzzle.
 * 
 * @author Martijn van de Rijdt
 */
public class MineCartMadnessPart2 extends MineCartMadness {

    private static final Logger LOGGER = LoggerFactory.getLogger(MineCartMadnessPart2.class);

    @Override
    public Point solve(State initialState) {
        State state = initialState;
        while (1 < state.getCarts().size()) {
            state = state.tick(false);
        }

        MineCart remainingCart = state.getCarts().iterator().next();
        
        return remainingCart.getLocation();
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
