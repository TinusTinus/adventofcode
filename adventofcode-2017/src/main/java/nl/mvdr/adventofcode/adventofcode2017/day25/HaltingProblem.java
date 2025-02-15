package nl.mvdr.adventofcode.adventofcode2017.day25;

import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nl.mvdr.adventofcode.solver.IntSolver;

/**
 * Solution to the day 25 puzzle of 2017's Advent of Code:
 * <a href="https://adventofcode.com/2017/day/25">The Halting Problem</a>.
 *
 * @author Martijn van de Rijdt
 */
public class HaltingProblem implements IntSolver {

    private static final Logger LOGGER = LoggerFactory.getLogger(HaltingProblem.class);
    
    /**
     * {@inheritDoc}
     * 
     * @return diagnostic checksum
     */
    @Override
    public int solve(Stream<String> lines) {
        Blueprint blueprint = Blueprint.parse(lines);
        TuringMachine machine = blueprint.getTuringMachineDefinition().createTuringMachine();
        
        for (int i = 0; i != blueprint.getSteps(); i++) {
            machine.executeStep();
        }
        
        return machine.checksum();
    }
    
    /**
     * Main method.
     * 
     * @param args commandline arguments; these are ignored
     */
    public static void main(String[] args) {
        HaltingProblem instance = new HaltingProblem();

        String result = instance.solve("input-day25-2017.txt");

        LOGGER.info(result);
    }
}
