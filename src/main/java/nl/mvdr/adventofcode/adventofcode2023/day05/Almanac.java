package nl.mvdr.adventofcode.adventofcode2023.day05;

import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * An almanac.
 *
 * @param seedRanges ranges of required seed numbers
 * @param maps the conversion maps in order; that is: seed-to-soil, soil-to-fertilizer, ... , humidity-to-location
 * @author Martijn van de Rijdt
 */
record Almanac(List<Range> seedRanges, List<ConversionMap> maps) {
    
    private static final String SEEDS_PREFIX = "seeds: ";

    /**
     * Parses puzzle input into an almanac.
     * 
     * @param lines puzzle input
     * @param individualSeeds whether seeds should be interpreted as individual values (as in part 1) rather than as ranges (as in part 2)
     * @return almanac
     */
    static Almanac parse(List<String> lines, boolean individualSeeds) {
        var seedRanges = parseSeeds(lines.get(0), individualSeeds);
        var maps = ConversionMap.parse(lines);
        return new Almanac(seedRanges, maps);
    }
    
    /**
     * Parses the line containing the list of seeds.
     * 
     * @param text first line of the puzzle input, for example: "seeds: 79 14 55 13"
     * @param individualSeeds whether seeds should be interpreted as individual values (as in part 1) rather than as ranges (as in part 2)
     * @return seed ranges
     */
    private static List<Range> parseSeeds(String text, boolean individualSeeds) {
        if (!text.startsWith(SEEDS_PREFIX)) {
            throw new IllegalArgumentException("Unable to parse as seeds: " + text);
        }
        var parts = text.substring(SEEDS_PREFIX.length()).split(" ");
        
        List<Range> result;
        if (individualSeeds) {
            // Interpret each value as an individual seed value
            result = Stream.of(parts)
                    .map(Long::valueOf)
                    .mapToLong(Long::longValue)
                    // Convert each value to a range of length 1
                    .mapToObj(seedNumber -> new Range(seedNumber, 1L))
                    .toList();
        } else {
            // Interpret pairs of values as a range specification
            if (parts.length % 2 != 0) {
                throw new IllegalArgumentException("Unable to parse as seed ranges: " + text);
            }
            result = IntStream.range(0, parts.length)
                    .filter(i -> i % 2 == 0)
                    .mapToObj(i -> new Range(Long.parseLong(parts[i]), Long.parseLong(parts[i + 1])))
                    .toList();
        }
        return result;
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
