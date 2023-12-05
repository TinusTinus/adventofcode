package nl.mvdr.adventofcode.adventofcode2023.day05;

import java.util.stream.Stream;

import nl.mvdr.adventofcode.LongSolver;

/**
 * Solution to <a href="https://adventofcode.com/2023/day/5">If You Give A Seed A Fertilizer</a>.
 *
 * @author Martijn van de Rijdt
 */
class IfYouGiveASeedAFertilizer implements LongSolver {

    private final boolean seedsAsRanges;
    
    /**
     * Constructor.
     * 
     * @param seedsAsRanges whether seeds should be interpreted as ranges (as in part 2 of the puzzle)
     */
    public IfYouGiveASeedAFertilizer(boolean seedsAsRanges) {
        super();
        this.seedsAsRanges = seedsAsRanges;
    }
    
    @Override
    public long solve(Stream<String> lines) {
        var almanac = Almanac.parse(lines.toList(), seedsAsRanges);
        return almanac.getLowestLocationNumber();
    }
}
 