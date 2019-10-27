package nl.mvdr.adventofcode.adventofcode2017.day25;

import java.io.IOException;
import java.nio.file.Path;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nl.mvdr.adventofcode.PathSolver;

/**
 * Solution to the day 25 puzzle of 2017's Advent of Code:
 * <a href="https://adventofcode.com/2017/day/25">The Halting Problem</a>.
 *
 * @author Martijn van de Rijdt
 */
public class HaltingProblem implements PathSolver<Integer> {

    private static final Logger LOGGER = LoggerFactory.getLogger(HaltingProblem.class);
    
    /**
     * {@inheritDoc}
     * 
     * @return diagnostic checksum
     */
    @Override
    public Integer solve(Path inputFilePath) throws IOException {
        Blueprint blueprint = Blueprint.parse(inputFilePath);
        TuringMachine machine = blueprint.getTuringMachineDefinition().createTuringMachine();
        
        for (int i = 0; i != blueprint.getSteps(); i++) {
            machine = machine.executeStep();
            
            // TODO clean up logging
            if (i % 100_000 == 99_999) {
                LOGGER.info("{} steps taken, current checksum value: {}", i, machine.checksum());
            }
        }
        
        return Integer.valueOf(machine.checksum());
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
