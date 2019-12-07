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
 * Helper class for generating sequences of phase settings.
 *
 * @author Martijn van de Rijdt
 */
abstract class AmplificationCircuit implements IntSolver {

    private static final Logger LOGGER = LoggerFactory.getLogger(AmplificationCircuit.class);

    private final int phaseSettingLowerBound;
    private final int phaseSettingUpperBound;
    
    /**
     * Constructor.
     * 
     * @param phaseSettingLowerBound lower bound for phase settings
     * @param phaseSettingUpperBound upper bound for phase settings
     */
    AmplificationCircuit(int phaseSettingLowerBound, int phaseSettingUpperBound) {
        super();
        this.phaseSettingLowerBound = phaseSettingLowerBound;
        this.phaseSettingUpperBound = phaseSettingUpperBound;
    }
    
    /**
     * {@inheritDoc}
     * 
     * @return highest signal that can be sent to the thrusters
     */
    @Override
    public int solve(Stream<String> lines) {
        Program program = Program.parse(lines.findFirst().orElseThrow());
        
        Set<List<Integer>> phaseSettingSequences = generatePhaseSettingSequences(phaseSettingLowerBound, phaseSettingUpperBound); 
        
        LOGGER.debug("Phase setting sequences: {}", phaseSettingSequences);
        
        return phaseSettingSequences.stream()
                .mapToInt(phaseSettingSequence -> computeThrusterSignal(program, phaseSettingSequence))
                .max()
                .getAsInt();
    }

    /**
     * Generates all possible sequences of phase settings within the given bounds.
     * 
     * @param lowerBound the lower bound phase setting, inclusive
     * @param upperBound the upper bound phase setting, exclusive
     * @return all possible phase setting sequences
     */
    private Set<List<Integer>> generatePhaseSettingSequences(int lowerBound, int upperBound) {
        Set<Integer> phaseSettings = IntStream.range(lowerBound, upperBound)
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
    abstract int computeThrusterSignal(Program initialProgram, List<Integer> phaseSettingSequence);
}
