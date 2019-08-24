package nl.mvdr.adventofcode.adventofcode2018.day24;

import java.io.IOException;
import java.nio.file.Path;
import java.util.stream.IntStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nl.mvdr.adventofcode.PathSolver;

/**
 * Solution to the day 24 puzzle of 2018's Advent of Code:
 * <a href="https://adventofcode.com/2018/day/24">Immune System Simulator 20XX</a>.
 *
 * @author Martijn van de Rijdt
 */
public class ImmuneSystemSimulatorPart2 implements PathSolver<Integer> {

    private static final Logger LOGGER = LoggerFactory.getLogger(ImmuneSystemSimulatorPart2.class);

    /**
     * {@inheritDoc}
     * 
     * @return how many units the winning army would have
     */
    @Override
    public Integer solve(Path inputFilePath) throws IOException {
        State inputState = State.parse(inputFilePath);
        
        // Start at 35:
        // * the computation takes a very long time for boost = 34;
        // * the immune system does not win for 0 <= boost <= 34.
        int minimumBoost = IntStream.range(35, 1570)
                .parallel()
                .peek(boost -> LOGGER.debug("Inspecting boost {}", Integer.valueOf(boost)))
                .filter(boost -> inputState.boostImmuneSystem(boost).fightUntilDone().winner().get() == Army.IMMUNE_SYSTEM)
                .peek(boost -> LOGGER.debug("Winner found: {}", Integer.valueOf(boost)))
                .min()
                .getAsInt();
        
        int result = inputState.boostImmuneSystem(minimumBoost).fightUntilDone().totalUnits();
        return Integer.valueOf(result);
    }
    
    /**
     * Main method.
     * 
     * @param args commandline arguments; these are ignored
     */
    public static void main(String[] args) {
        ImmuneSystemSimulatorPart2 solver = new ImmuneSystemSimulatorPart2();
        String solution = solver.solve("input-day24-2018.txt");
        LOGGER.info(solution);
    }
}
