package nl.mvdr.adventofcode.adventofcode2018.day24;

import java.io.IOException;
import java.nio.file.Path;

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
        
        int boost = 1;
        State state = inputState.boostImmuneSystem(boost);
        State endState = state.fightUntilDone();
        
        while(endState.winner().get().equals(Army.INFECTION)) {
            LOGGER.debug("Boost: {}", boost);
            state = inputState.boostImmuneSystem(boost);
            endState = state.fightUntilDone();
            boost++;
        }
        
        return endState.totalUnits();
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
