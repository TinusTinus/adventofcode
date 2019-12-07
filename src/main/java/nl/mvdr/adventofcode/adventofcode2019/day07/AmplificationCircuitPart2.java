package nl.mvdr.adventofcode.adventofcode2019.day07;

import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

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
        
        List<BlockingQueue<Integer>> queues = createQueues(phaseSettingSequence);
        
        ExecutorService executorService = Executors.newFixedThreadPool(queues.size());
        for (int i = 0; i != queues.size(); i++) {
            BlockingQueue<Integer> input = queues.get(i);
            BlockingQueue<Integer> output = queues.get((i + 1) % queues.size());
            
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
        return queues.get(0).remove().intValue();
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
    private List<BlockingQueue<Integer>> createQueues(List<Integer> phaseSettingSequence) {
        List<BlockingQueue<Integer>> queues = phaseSettingSequence.stream()
                .map(phaseSetting -> {
                    BlockingQueue<Integer> result = new LinkedBlockingQueue<>();
                    // The first element of each queue is the amp's phase setting.
                    result.add(phaseSetting);
                    return result;
                })
                .collect(Collectors.toList());
        
        // The first amp receives a single 0 input.
        queues.get(0).add(Integer.valueOf(0));
        
        return queues;
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
