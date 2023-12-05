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
record Almanac(List<Long> seeds, List<ConversionMap> maps) {
    
    private static final String SEEDS_PREFIX = "seeds: ";

    /**
     * Parses puzzle input into an almanac.
     * 
     * @param lines puzzle input
     * @return almanac
     */
    static Almanac parse(List<String> lines) {
        var seeds = parseSeeds(lines.get(0));
        var maps = List.<ConversionMap>of(); // TODO
        return new Almanac(seeds, maps);
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
     * Maps a seed number to a location number, by applying each conversion map in order.
     * @param seedNumber seed number
     * @return location number
     */
    private long map(long seedNumber) {
        var currentNumber = seedNumber;
        for (ConversionMap map : maps) {
            currentNumber = map.map(currentNumber);
        }
        return currentNumber;
    }
    
    /**
     * @return the lowest location number
     */
    long getLowestLocationNumber() {
        return seeds.stream()
                .mapToLong(Long::longValue)
                .map(this::map)
                .min()
                .orElseThrow();
    }
}
