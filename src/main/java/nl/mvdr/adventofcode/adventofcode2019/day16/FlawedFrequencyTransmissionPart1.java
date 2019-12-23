package nl.mvdr.adventofcode.adventofcode2019.day16;

import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nl.mvdr.adventofcode.LinesSolver;

/**
 * Solution to the day 16 puzzle of 2019's Advent of Code:
 * <a href="https://adventofcode.com/2019/day/16">Flawed Frequency Transmission</a>.
 *
 * @author Martijn van de Rijdt
 */
public class FlawedFrequencyTransmissionPart1 implements LinesSolver<String> {

    private static final Logger LOGGER = LoggerFactory.getLogger(FlawedFrequencyTransmissionPart1.class);

    private final int phases;

    /** Constructor. */
    public FlawedFrequencyTransmissionPart1() {
        this(100);
    }
    
    /**
     * Constructor.
     * 
     * @param phases number of phases of computation to perform
     */
    FlawedFrequencyTransmissionPart1(int phases) {
        super();
        this.phases = phases;
    }
    
    /**
     * {@inheritDoc}
     * 
     * @return first eight digits in the final output list
     */
    @Override
    public String solve(Stream<String> lines) {
        String input = lines.findFirst().orElseThrow();
        
        return FlawedFrequencyTransmissions.fft(input, phases, 1, 0L);
    }
    
    /**
     * Main method.
     * 
     * @param args commandline arguments; these are ignored
     */
    public static void main(String[] args) {
        FlawedFrequencyTransmissionPart1 instance = new FlawedFrequencyTransmissionPart1();

        String result = instance.solve("input-day16-2019.txt");

        LOGGER.info(result);
    }
}
 