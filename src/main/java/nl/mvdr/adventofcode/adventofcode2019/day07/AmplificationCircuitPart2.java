package nl.mvdr.adventofcode.adventofcode2019.day07;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nl.mvdr.adventofcode.adventofcode2019.intcode.Program;

/**
 * Solution to the day 7 puzzle of 2019's Advent of Code:
 * <a href="https://adventofcode.com/2019/day/6">Amplification Circuit</a>.
 *
 * @author Martijn van de Rijdt
 */
public class AmplificationCircuitPart2 extends AmplificationCircuit {

    private static final Logger LOGGER = LoggerFactory.getLogger(AmplificationCircuitPart2.class);
    
    /** Constructor. */
    public AmplificationCircuitPart2() {
        super(5, 10);
    }

    @Override
    int computeThrusterSignal(Program initialProgram, List<Integer> phaseSettingSequence) {
        // TODO implement
        return 0;
    }

    /**
     * Main method.
     * 
     * @param args commandline arguments; these are ignored
     */
    public static void main(String[] args) {
        AmplificationCircuitPart2 instance = new AmplificationCircuitPart2();

        String result = instance.solve("input-day07-2019.txt");

        LOGGER.info(result);
    }
}
