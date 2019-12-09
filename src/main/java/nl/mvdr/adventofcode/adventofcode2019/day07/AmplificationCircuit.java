package nl.mvdr.adventofcode.adventofcode2019.day07;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nl.mvdr.adventofcode.LongSolver;
import nl.mvdr.adventofcode.adventofcode2019.intcode.Program;

/**
 * Helper class for generating sequences of phase settings.
 *
 * @author Martijn van de Rijdt
 */
abstract class AmplificationCircuit implements LongSolver {

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
    public long solve(Stream<String> lines) {
        Program program = Program.parse(lines.findFirst().orElseThrow());
        
        Set<List<Long>> phaseSettingSequences = generatePhaseSettingSequences(phaseSettingLowerBound, phaseSettingUpperBound); 
        
        LOGGER.debug("Phase setting sequences: {}", phaseSettingSequences);
        
        return phaseSettingSequences.stream()
                .parallel()
                .mapToLong(phaseSettingSequence -> computeThrusterSignal(program, phaseSettingSequence))
                .max()
                .getAsLong();
    }

    /**
     * Generates all possible sequences of phase settings within the given bounds.
     * 
     * @param lowerBound the lower bound phase setting, inclusive
     * @param upperBound the upper bound phase setting, exclusive
     * @return all possible phase setting sequences
     */
    private Set<List<Long>> generatePhaseSettingSequences(int lowerBound, int upperBound) {
        Set<Long> phaseSettings = IntStream.range(lowerBound, upperBound)
                .asLongStream()
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
    private long computeThrusterSignal(Program initialProgram, List<Long> phaseSettingSequence) {
        
        List<BlockingQueue<Long>> queues = createQueues(phaseSettingSequence);
        
        ExecutorService executorService = Executors.newFixedThreadPool(queues.size());
        for (int i = 0; i != queues.size(); i++) {
            BlockingQueue<Long> input = queues.get(i);
            BlockingQueue<Long> output = queues.get((i + 1) % queues.size());
            
            Program amplifier = initialProgram.withInput(input).withOutput(output);
            
            executorService.submit(amplifier::execute);
        }
        executorService.shutdown();
        
        try {
            executorService.awaitTermination(10L, TimeUnit.MINUTES);
        } catch (InterruptedException e) {
            LOGGER.error("Unexpected interrupt.", e);
            Thread.currentThread().interrupt();
        }
        
        // The output queue for the last amp is the input queue for the first amp,
        // that is, the first queue.
        // After termination this is also the only nonempty queue.
        return queues.get(0).remove().longValue();
    }

    /**
     * Creates blocking queues, which can serve as input / output for the
     * amplifiers.
     * 
     * Each queue should serve as the input for one amplifier and the output for a
     * neighbouring amplifier.
     * 
     * @param phaseSettingSequence phase setting sequence
     * @return input / output queues
     */
    private List<BlockingQueue<Long>> createQueues(List<Long> phaseSettingSequence) {
        List<BlockingQueue<Long>> queues = phaseSettingSequence.stream()
                .map(phaseSetting -> {
                    BlockingQueue<Long> result = new LinkedBlockingQueue<>();
                    // The first element of each queue is the amp's phase setting.
                    result.add(phaseSetting);
                    return result;
                })
                .collect(Collectors.toList());
        
        // The first amp receives a single 0 input.
        queues.get(0).add(Long.valueOf(0));
        
        return queues;
    }
}
