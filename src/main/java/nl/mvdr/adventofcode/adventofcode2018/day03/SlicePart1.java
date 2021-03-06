package nl.mvdr.adventofcode.adventofcode2018.day03;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Solution to the day 3 puzzle of 2018's Advent of Code:
 * <a href="https://adventofcode.com/2018/day/3">No Matter How You Slice It</a>.
 *
 * @author Martijn van de Rijdt
 */
public class SlicePart1 extends Slice<Long> {

    private static final Logger LOGGER = LoggerFactory.getLogger(SlicePart1.class);
    
    @Override
    protected Long solve(List<Claim> claims, Map<SquareInch, Set<Claim>> claimedFabric) {
        long result = claimedFabric.values().stream()
                .filter(cs -> 2 <= cs.size())
                .count();
        return Long.valueOf(result);
    }

    /**
     * Main method.
     * 
     * @param args commandline arguments; these are ignored
     */
    public static void main(String[] args) {
        SlicePart1 instance = new SlicePart1();

        String result = instance.solve("input-day03-2018.txt");

        LOGGER.info(result);
    }
}
