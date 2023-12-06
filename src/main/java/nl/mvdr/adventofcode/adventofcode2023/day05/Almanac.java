package nl.mvdr.adventofcode.adventofcode2023.day05;

import java.util.List;

/**
 * An almanac.
 *
 * @param seedRanges ranges of required seed numbers
 * @param maps the conversion maps in order; that is: seed-to-soil, soil-to-fertilizer, ... , humidity-to-location
 * @author Martijn van de Rijdt
 */
record Almanac(List<Range> seedRanges, List<ConversionMap> maps) {
    
    /**
     * Parses puzzle input into an almanac.
     * 
     * @param lines puzzle input
     * @param individualSeeds whether seeds should be interpreted as individual values (as in part 1) rather than as ranges (as in part 2)
     * @return almanac
     */
    static Almanac parse(List<String> lines, boolean individualSeeds) {
        var seedRanges = Range.parseSeeds(lines.get(0), individualSeeds);
        var maps = ConversionMap.parse(lines);
        return new Almanac(seedRanges, maps);
    }
    
    /**
     * Maps ranges of seed numbers to ranges of location numbers, by applying each conversion map in order.
     * 
     * @param ranges ranges of seed numbers
     * @return ranges of location numbers
     */
    private List<Range> map(List<Range> ranges) {
        var currentRanges = ranges;
        for (ConversionMap map : maps) {
            currentRanges = map.map(currentRanges);
        }
        return currentRanges;
    }
    
    /**
     * @return the lowest location number
     */
    long getLowestLocationNumber() {
        var locationRanges = map(seedRanges);
        return locationRanges.stream()
                .filter(Range::nonEmpty)
                .mapToLong(Range::start)
                .min()
                .orElseThrow();
    }
}
