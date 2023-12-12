package nl.mvdr.adventofcode.adventofcode2023.day12;

import java.util.List;
import java.util.stream.Stream;

/**
 * A (possibly damaged) record of the conditions of a row of springs.
 *
 * @author Martijn van de Rijdt
 */
record ConditionRecord(List<Condition> springs, List<Integer> contiguousGroupSizes) {
    
    /**
     * Parses a line from the puzzle input.
     *  
     * @param line string representation of a single condition record, for example: "???.### 1,1,3"
     * @return the condition record represented by the line
     */
    static ConditionRecord parse(String line) {
        var parts = line.split(" ");
        if (parts.length != 2) {
            throw new IllegalArgumentException("Unable to parse condition record: " + line);
        }
        var springs = Condition.parse(parts[0]);
        var contiguousGroupSizes = parseContiguousGroupSizes(parts[1]);
        return new ConditionRecord(springs, contiguousGroupSizes);
    }
    
    /**
     * Parses the string representation of the sizes of each contiguous group of damaged springs in a single row.
     * 
     * @param representation string representation of contiguous group sizes, for example: "1,1,3"
     * @return list of sizes
     */
    private static List<Integer> parseContiguousGroupSizes(String representation) {
        var parts = representation.split(",");
        return Stream.of(parts)
                .map(Integer::valueOf)
                .toList();
    }
    
    /**
     * @return number of possible spring arrangements conforming to this record
     */
    long countArrangements() {
        return 0L; // TODO implement
    }
}
