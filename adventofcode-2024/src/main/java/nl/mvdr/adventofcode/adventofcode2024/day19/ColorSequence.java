package nl.mvdr.adventofcode.adventofcode2024.day19;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

record ColorSequence(List<Color> colors) {

    private static Map<ColorSequence, Long> cache = new HashMap<>();
    
    static ColorSequence parse(String stringRepresentation) {
        var colors = stringRepresentation.chars()
                .mapToObj(c -> Color.parse((char)c))
                .toList();
        return new ColorSequence(colors);
    }
    
    private boolean startsWith(ColorSequence towelPattern) {
        return towelPattern.colors().size() <= colors.size()
                && towelPattern.colors().equals(colors.subList(0, towelPattern.colors().size()));
    }
    
    private ColorSequence dropColors(int n) {
        return new ColorSequence(colors.subList(n, colors.size()));
    }
    
    /// Checks whether it is possible to create this design.
    boolean isPossible(Set<ColorSequence> towelPatterns) {
        return colors.isEmpty()
                || towelPatterns.stream()
                        .filter(this::startsWith)
                        .anyMatch(towelPattern -> dropColors(towelPattern.colors().size()).isPossible(towelPatterns));
    }

    /// Counts the ways to create this design.
    long countWaysToMake(Set<ColorSequence> towelPatterns) {
        Long cached = cache.get(this);
        
        long result;
        if (cached == null) {
            if (colors.isEmpty()) {
                result = 1L;
            } else {
                result = towelPatterns.stream()
                        .filter(this::startsWith)
                        .mapToLong(towelPattern -> dropColors(towelPattern.colors().size()).countWaysToMake(towelPatterns))
                        .sum();
            }
            cache.put(this, Long.valueOf(result));
        } else {
            result = cached.longValue();
        }
        
        return result;
    }
}
