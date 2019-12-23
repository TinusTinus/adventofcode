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
public class FlawedFrequencyTransmissionPart2 implements LinesSolver<String> {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(FlawedFrequencyTransmissionPart2.class);

    /**
     * {@inheritDoc}
     * 
     * @return eight-digit message in the final output list
     */
    @Override
    public String solve(Stream<String> lines) {
        String input = lines.findFirst().orElseThrow();
        
        long offset = Long.parseLong(input.substring(0, 7));
        
        return FlawedFrequencyTransmissions.fft(input, 100, 10_000, offset);
    }
    
    /**
     * Main method.
     * 
     * @param args commandline arguments; these are ignored
     */
    public static void main(String[] args) {
        FlawedFrequencyTransmissionPart2 instance = new FlawedFrequencyTransmissionPart2();

        String result = instance.solve("input-day16-2019.txt");

        LOGGER.info(result);
    }
}
 