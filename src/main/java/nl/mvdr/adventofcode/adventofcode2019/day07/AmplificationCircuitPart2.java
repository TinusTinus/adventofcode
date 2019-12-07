package nl.mvdr.adventofcode.adventofcode2019.day07;

import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.function.IntConsumer;
import java.util.function.IntSupplier;
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
        
        // Create blocking queues.
        // Each queue serves as the input for one amplifier and the output for a neighbouring amplifier.
        // The first element of each queue is the amp's phase setting.
        List<BlockingQueue<Integer>> queues = phaseSettingSequence.stream()
                .map(phaseSetting -> {
                    BlockingQueue<Integer> result = new LinkedBlockingQueue<>();
                    result.add(phaseSetting);
                    return result;
                })
                .collect(Collectors.toList());
        // The first amp receives a single 0 input.
        queues.get(0).add(Integer.valueOf(0));
        
        ExecutorService executorService = Executors.newFixedThreadPool(queues.size());
        for (int i = 0; i != queues.size(); i++) {
            Program program = initialProgram.withInput(asInput(queues.get(i)));
            program = program.withOutput(asOutput(queues.get((i + 1) % queues.size())));
            executorService.submit(program::execute);
        }
        executorService.shutdown();
        
        try {
            executorService.awaitTermination(10L, TimeUnit.MINUTES);
        } catch (InterruptedException e) {
            handle(e);
        }
        
        return queues.get(0).remove().intValue();
    }

    private IntSupplier asInput(BlockingQueue<Integer> queue) {
        return () -> {
            int result;
            try {
                result = queue.take().intValue();
            } catch (InterruptedException e) {
                handle(e);
                result = 0;
            }
            return result;
        };
    }
    
    private IntConsumer asOutput(BlockingQueue<Integer> queue) {
        return value -> {
            try {
                queue.put(Integer.valueOf(value));
            } catch (InterruptedException e) {
                handle(e);
            }
        };
    }

    /**
     * Handles an {@link InterruptedException}.
     * 
     * @param interruptedException exception
     */
    private void handle(InterruptedException interruptedException) {
        LOGGER.error("Unexpected interrupt.", interruptedException);
        Thread.currentThread().interrupt();
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
