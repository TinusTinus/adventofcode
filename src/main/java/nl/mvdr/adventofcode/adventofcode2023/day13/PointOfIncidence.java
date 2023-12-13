package nl.mvdr.adventofcode.adventofcode2023.day13;

import java.util.stream.Stream;

import nl.mvdr.adventofcode.IntSolver;

/**
 * Solution to <a href="https://adventofcode.com/2023/day/13">Point of Incidence</a>.
 *
 * @author Martijn van de Rijdt
 */
public class PointOfIncidence implements IntSolver {
    
    private final int smudges;
    
    PointOfIncidence(int smudges) {
        super();
        this.smudges = smudges;
    }
    
    @Override
    public int solve(Stream<String> lines) {
        var patterns = Pattern.parse(lines.toList());
        return patterns.stream()
                .mapToInt(pattern -> pattern.summarize(smudges))
                .sum();
    }
}
 