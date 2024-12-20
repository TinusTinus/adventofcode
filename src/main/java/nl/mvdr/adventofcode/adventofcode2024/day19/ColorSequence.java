package nl.mvdr.adventofcode.adventofcode2024.day19;

import java.util.List;
import java.util.Set;

record ColorSequence(List<Color> colors) {

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
    
    /// Checks whether it is possible to create this design,
    /// using an infinite number of towels of each of the given towel patterns.
    boolean isPossible(Set<ColorSequence> towelPatterns) {
        return colors.isEmpty()
                || towelPatterns.stream()
                        .filter(this::startsWith)
                        .anyMatch(towelPattern -> dropColors(towelPattern.colors().size()).isPossible(towelPatterns));
    }

    /// Checks whether it is possible to create this design,
    /// using an infinite number of towels of each of the given towel patterns.
    long countWaysToMake(Set<ColorSequence> towelPatterns) {
        long result;
        if (colors.isEmpty()) {
            result = 1L;
        } else {
            result = towelPatterns.stream()
                    .filter(this::startsWith)
                    .mapToLong(towelPattern -> dropColors(towelPattern.colors().size()).countWaysToMake(towelPatterns))
                    .sum();
        }
        return result;
    }
}
