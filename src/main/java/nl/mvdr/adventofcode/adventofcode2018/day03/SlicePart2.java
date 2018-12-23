package nl.mvdr.adventofcode.adventofcode2018.day03;

import java.util.Map;
import java.util.Set;

/**
 * Solution to the day 3 puzzle of 2018's Advent of Code:
 * <a href="https://adventofcode.com/2018/day/3">No Matter How You Slice It</a>.
 *
 * @author Martijn van de Rijdt
 */
public class SlicePart2 extends Slice {

    @Override
    protected String solve(Map<SquareInch, Set<Claim>> claimedFabric) {
        // Nope, this is wrong.
        return "" + claimedFabric.values().stream()
                .filter(cs -> cs.size() == 1)
                .map(cs -> cs.iterator().next())
                .mapToInt(Claim::getId)
                .findAny()
                .getAsInt();
    }
    
    /**
     * Main method.
     * 
     * @param args commandline arguments; these are ignored
     */
    public static void main(String[] args) {
        SlicePart2 instance = new SlicePart2();

        String result = instance.solve("input-day03-2018.txt");

        System.out.println(result);
    }
}
