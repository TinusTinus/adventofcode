package nl.mvdr.adventofcode.adventofcode2018.day03;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Solution to the day 3 puzzle of 2018's Advent of Code:
 * <a href="https://adventofcode.com/2018/day/3">No Matter How You Slice It</a>.
 *
 * @author Martijn van de Rijdt
 */
public class SlicePart1 extends Slice {

    @Override
    protected String solve(List<Claim> claims, Map<SquareInch, Set<Claim>> claimedFabric) {
        return "" + claimedFabric.values().stream()
                .filter(cs -> 2 <= cs.size())
                .count();
    }

    /**
     * Main method.
     * 
     * @param args commandline arguments; these are ignored
     */
    public static void main(String[] args) {
        SlicePart1 instance = new SlicePart1();

        String result = instance.solve("input-day03-2018.txt");

        System.out.println(result);
    }
}
