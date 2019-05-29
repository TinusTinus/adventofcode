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
public class SlicePart2 extends Slice {

    private static final Logger LOGGER = LoggerFactory.getLogger(SlicePart2.class);
    
    @Override
    protected String solve(List<Claim> claims, Map<SquareInch, Set<Claim>> claimedFabric) {
        return "" + claims.stream()
                .filter(claim -> isUncontested(claim, claimedFabric))
                .mapToInt(Claim::getId)
                .findAny()
                .getAsInt();
    }
    
    private boolean isUncontested(Claim claim, Map<SquareInch, Set<Claim>> claimedFabric) {
        return claimedFabric.values().stream()
                .allMatch(set -> !set.contains(claim) || set.size() == 1); 
    }
    
    /**
     * Main method.
     * 
     * @param args commandline arguments; these are ignored
     */
    public static void main(String[] args) {
        SlicePart2 instance = new SlicePart2();

        String result = instance.solve("input-day03-2018.txt");

        LOGGER.info(result);
    }
}
