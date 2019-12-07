package nl.mvdr.adventofcode.adventofcode2019.day07;

import java.util.ArrayList;
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
public class AmplificationCircuitPart1 extends AmplificationCircuit {

    private static final Logger LOGGER = LoggerFactory.getLogger(AmplificationCircuitPart1.class);
    
    /** Constructor. */
    public AmplificationCircuitPart1() {
        super(0, 5);
    }
    
    @Override
    int computeThrusterSignal(Program initialProgram, List<Integer> phaseSettingSequence) {
        int result = 0;
        for (Integer phaseSetting : phaseSettingSequence) {
            List<Integer> outputValues = new ArrayList<>();
            
            initialProgram.withInput(phaseSetting.intValue(), result)
                    .withOutput(outputValues::add)
                    .execute();
            
            // There should be exactly 1 output value
            if (outputValues.size() != 1) {
                throw new IllegalStateException("Unexpected output: " + outputValues);
            }
            result = outputValues.get(0).intValue();
        }
        
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Phase setting sequence {}: thruster signal {}", phaseSettingSequence, Integer.valueOf(result));
        }
        
        return result;
    }

    /**
     * Main method.
     * 
     * @param args commandline arguments; these are ignored
     */
    public static void main(String[] args) {
        AmplificationCircuitPart1 instance = new AmplificationCircuitPart1();

        String result = instance.solve("input-day07-2019.txt");

        LOGGER.info(result);
    }
}
