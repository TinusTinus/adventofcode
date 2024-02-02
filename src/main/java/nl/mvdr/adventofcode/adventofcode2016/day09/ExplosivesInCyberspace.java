package nl.mvdr.adventofcode.adventofcode2016.day09;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import nl.mvdr.adventofcode.LongSolver;

/**
 * Solution to the day 9 puzzle of 2016's Advent of Code:
 * <a href="https://adventofcode.com/2016/day/9">Explosives in Cyberspace</a>.
 *
 * @author Martijn van de Rijdt
 */
abstract class ExplosivesInCyberspace implements LongSolver {

    /** Whether to recursively perform expansion of compressed data. */
    private final boolean recursiveExpansion;
    
    /**
     * Cache containing previously computed partial results.
     * 
     * Keys: compressed sequences.
     * Values: for each sequence, the length of its expanded version.
     */
    private final Map<String, Long> cache;
    
    /**
     * Constructor.
     * 
     * @param recursiveExpansion whether to recursively perform expansion of compressed data
     */
    ExplosivesInCyberspace(boolean recursiveExpansion) {
        super();
        this.recursiveExpansion = recursiveExpansion;
        this.cache = new HashMap<>();
    }
    
    /**
     * {@inheritDoc}
     * 
     * @return length of the decompressed text
     */
    @Override
    public long solve(Stream<String> lines) {
        String text = lines.findFirst().orElseThrow();
        return computeExpandedLength(text);
    }

    /**
     * Computes the length of the expanded version of the given compressed text.
     * 
     * @param text compressed text
     * @return length of the given text after expansion
     */
    private long computeExpandedLength(String text) {
        String remainingText = text;
        long result = 0;
        
        while (!remainingText.isEmpty()) {
            int index = remainingText.indexOf("(");
            if (index == -1) {
                // No more compressed sequences.
                result += remainingText.length();
                remainingText = "";
            } else if (index == 0) {
                // remainingText starts with "("; this may be the start of a compressed sequence.
                Pattern pattern = Pattern.compile("\\((\\d*)x(\\d*)\\)(.*)");
                Matcher matcher = pattern.matcher(remainingText);
                if (matcher.matches()) {
                    // Start of a compressed sequence!
                    int characters = Integer.parseInt(matcher.group(1));
                    int repeats = Integer.parseInt(matcher.group(2));
                    if (recursiveExpansion) {
                        // Expand the sequence and (recursively) lookup / compute its length.
                        String sequence = matcher.group(3).substring(0, characters);
                        String expandedSequence = expand(sequence, repeats);
                        result += getExpandedLength(expandedSequence);
                    } else {
                        // No need to actually expand the sequence: just count the expanded sequence's length.
                        result += characters * repeats;
                    }
                    remainingText = matcher.group(3).substring(characters);
                } else {
                    // Just an opening bracket, not the start of a compressed sequence.
                    result++;
                    remainingText = remainingText.substring(1);
                }
            } else {
                // remainingText contain a "(", but not at its start.
                result += index;
                remainingText = remainingText.substring(index);
            }
        }
        return result;
    }
    
    
    /**
     * Expands the given sequence.
     * 
     * This method performs one expansion; it does not expand recursively.
     * 
     * @param sequence compressed sequence
     * @param repeats number of repeats
     * @return expanded sequence
     */
    private String expand(String sequence, int repeats) {
        return IntStream.range(0, repeats)
                .mapToObj(i -> sequence)
                .collect(Collectors.joining());
    }
    
    /**
     * Returns the length of the expanded version of the given compressed text.
     * 
     * This method inspects the {@link #cache} first, and only performs any computation if necessary.
     * 
     * @param text compressed text
     * @return length of the given text after expansion
     */
    private long getExpandedLength(String text) {
        // Note: this method can be called recursively, so cache.computeIfAbsent(...)
        // would result in a ConcurrentModificationException.
        
        Long result = cache.get(text);
        
        if (result == null) {
            result = Long.valueOf(computeExpandedLength(text));
            cache.put(text, result);
        }
        
        return result.longValue();
    }
}
