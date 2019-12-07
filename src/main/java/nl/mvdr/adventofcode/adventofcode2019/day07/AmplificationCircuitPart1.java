package nl.mvdr.adventofcode.adventofcode2019.day07;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nl.mvdr.adventofcode.IntSolver;
import nl.mvdr.adventofcode.adventofcode2019.intcode.Program;

/**
 * Solution to the day 7 puzzle of 2019's Advent of Code:
 * <a href="https://adventofcode.com/2019/day/6">Amplification Circuit</a>.
 *
 * @author Martijn van de Rijdt
 */
public class AmplificationCircuitPart1 implements IntSolver {

    private static final Logger LOGGER = LoggerFactory.getLogger(AmplificationCircuitPart1.class);
    
    /**
     * {@inheritDoc}
     * 
     * @return highest signal that can be sent to the thrusters
     */
    @Override
    public int solve(Stream<String> lines) {
        Program program = Program.parse(lines.findFirst().orElseThrow());
        
        Set<List<Integer>> phaseSettingSequences = PhaseSettingSequences.generatePhaseSettingSequences(0, 5); 
        
        LOGGER.debug("Phase setting sequences: {}", phaseSettingSequences);
        
        return phaseSettingSequences.stream()
                .mapToInt(phaseSettingSequence -> computeThrusterSignal(program, phaseSettingSequence))
                .max()
                .getAsInt();
    }

    /**
     * Computes the thruster signal using the given program and phase settings.
     * 
     * @param initialProgram program as parsed from the puzzle input
     * @param phaseSettingSequence phase setting sequence
     * @return thruster signal
     */
    private int computeThrusterSignal(Program initialProgram, List<Integer> phaseSettingSequence) {
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
