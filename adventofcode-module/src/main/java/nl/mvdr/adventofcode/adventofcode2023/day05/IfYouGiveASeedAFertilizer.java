package nl.mvdr.adventofcode.adventofcode2023.day05;

import java.util.stream.Stream;

import nl.mvdr.adventofcode.solver.LongSolver;

/**
 * Solution to <a href="https://adventofcode.com/2023/day/5">If You Give A Seed A Fertilizer</a>.
 *
 * @author Martijn van de Rijdt
 */
class IfYouGiveASeedAFertilizer implements LongSolver {

    private final boolean individualSeeds;
    
    /**
     * Constructor.
     * 
     * @param individualSeeds whether seeds should be interpreted as individual values (as in part 1) rather than as ranges (as in part 2)
     */
    public IfYouGiveASeedAFertilizer(boolean individualSeeds) {
        super();
        this.individualSeeds = individualSeeds;
    }
    
    @Override
    public long solve(Stream<String> lines) {
        var almanac = Almanac.parse(lines.toList(), individualSeeds);
        return almanac.getLowestLocationNumber();
    }
}
 