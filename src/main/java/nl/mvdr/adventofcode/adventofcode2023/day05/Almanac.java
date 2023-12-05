package nl.mvdr.adventofcode.adventofcode2023.day05;

import java.util.List;
import java.util.stream.Stream;

/**
 * An almanac.
 *
 * @param seeds the required seeds
 * @param maps the conversion maps in order; that is: seed-to-soil, soil-to-fertilizer, ... , humidity-to-location
 * @author Martijn van de Rijdt
 */
record Almanac(List<Range> seedRanges, List<ConversionMap> maps) {
    
    private static final String SEEDS_PREFIX = "seeds: ";

    /**
     * Parses puzzle input into an almanac.
     * 
     * @param lines puzzle input
     * @return almanac
     */
    static Almanac parse(List<String> lines) {
        var seeds = parseSeeds(lines.get(0));
        var seedRanges = seeds.stream()
                .mapToLong(Long::longValue)
                .mapToObj(seedNumber -> new Range(seedNumber, 1))
                .toList();
        var maps = ConversionMap.parse(lines);
        return new Almanac(seedRanges, maps);
    }
    
    /**
     * Parses the line containing the list of seeds.
     * 
     * @param text first line of the puzzle input, for example: "seeds: 79 14 55 13"
     * @return seeds
     */
    private static List<Long> parseSeeds(String text) {
        if (!text.startsWith(SEEDS_PREFIX)) {
            throw new IllegalArgumentException("Unable to parse as seeds: " + text);
        }
        var parts = text.substring(SEEDS_PREFIX.length()).split(" ");
        return Stream.of(parts)
                .map(Long::valueOf)
                .toList();
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
