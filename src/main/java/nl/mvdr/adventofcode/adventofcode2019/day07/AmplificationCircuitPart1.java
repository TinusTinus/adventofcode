package nl.mvdr.adventofcode.adventofcode2019.day07;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
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
        
        Set<List<Integer>> phaseSettingSequences = generatePhaseSettingSequences(); 
        
        LOGGER.debug("Phase setting sequences: {}", phaseSettingSequences);
        
        return phaseSettingSequences.stream()
                .mapToInt(phaseSettingSequence -> computeThrusterSignal(program, phaseSettingSequence))
                .max()
                .getAsInt();
    }

    /** @return all possible phase setting sequences */
    private Set<List<Integer>> generatePhaseSettingSequences() {
        Set<Integer> phaseSettings = IntStream.range(0, 5)
                .boxed()
                .collect(Collectors.toSet());
        
        return generateAllPermutations(phaseSettings);
    }
    
    /**
     * Generates all permutations of the given set of values.
     * 
     * @param <T> value type
     * @param values values
     * @return set containing all possible permutations of the given values
     */
    private <T> Set<List<T>> generateAllPermutations(Set<T> values) {
        Set<List<T>> result;
        
        if (values.isEmpty()) {
            result = Set.of();
        } else if (values.size() == 1) {
            T value = values.iterator().next();
            result = Set.of(List.of(value));
        } else {
            result = new HashSet<>();
            for (T value : values) {
                Set<T> remaining = new HashSet<>(values);
                remaining.remove(value);
                // Recursively determine all permutations of the remaining values
                Set<List<T>> permutations = generateAllPermutations(remaining);
                for (List<T> permutation : permutations) {
                    List<T> resultPermutation = new ArrayList<>();
                    resultPermutation.add(value);
                    resultPermutation.addAll(permutation);
                    result.add(resultPermutation);
                }
            }
        }
        return result;
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
