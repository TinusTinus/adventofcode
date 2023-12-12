package nl.mvdr.adventofcode.adventofcode2023.day12;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A (possibly damaged) record of the conditions of a row of springs.
 *
 * @author Martijn van de Rijdt
 */
record ConditionRecord(List<Condition> springs, List<Integer> contiguousGroupSizes) {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(ConditionRecord.class);
    
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
        long result;
        if (contiguousGroupSizes.isEmpty() && !springs.contains(Condition.DAMAGED)) {
            // This is a single valid arrangement: all springs in this row must be operational.
            result = 1L;
        } else if (springs.isEmpty()) {
            // There are contiguous group sizes (see the previous guard).
            // Therefore this is an invalid arrangement.
            result = 0L;
        } else if (contiguousGroupSizes.isEmpty()) {
            // There are damaged springs (see the first guard).
            // Therefore this is an invalid arrangement.
            result = 0L;
        } else if (springs.getFirst() == Condition.OPERATIONAL) {
            // We can just ignore this spring. Inspect the rest of the row.
            result = dropFirstSpring().countArrangements();
        } else if (couldStartWithContiguousGroup()) {
            var groupSize = contiguousGroupSizes.getFirst().intValue();
            if (springs.size() == groupSize) {
                result = new ConditionRecord(List.of(), contiguousGroupSizes.subList(1, contiguousGroupSizes.size()))
                        .countArrangements();
            } else {
                // Compute the number of valid arrangements, assuming the contiguous group does start here.
                var newSprings = springs.subList(groupSize + 1, springs.size());
                var newGroupSizes = contiguousGroupSizes.subList(1, contiguousGroupSizes.size());
                result = new ConditionRecord(newSprings, newGroupSizes).countArrangements();
                
                if (springs.getFirst() == Condition.UNKNOWN) {
                    // The first spring could also be operational.
                    result = result + dropFirstSpring().countArrangements();
                }
            }
        } else {
            // No contiguous group can start here.
            result = switch(springs.getFirst()) {
                case DAMAGED -> 0L; // Invalid record.
                case UNKNOWN -> dropFirstSpring().countArrangements(); // First spring must be operational.
                case OPERATIONAL -> throw new IllegalStateException("Unexpected operational spring found at the start of " + this); // Should be prevented by earlier checks
                default -> throw new IllegalStateException("Unexpected condition: " + springs.getFirst());
            };
        }
        
        LOGGER.debug("Count for {}: {}", this, Long.valueOf(result));
        
        return result;
    }
    
    /**
     * @return whether the first springs of this record could potentially form a contiguous group of damanged springs
     */
    private boolean couldStartWithContiguousGroup() {
        boolean result;
        if (contiguousGroupSizes.isEmpty()) {
            result = false;
        } else {
            var groupSize = contiguousGroupSizes.getFirst().intValue();
            if (springs.size() < groupSize) {
                result = false;
            } else {
                var potentialGroup = springs.subList(0, groupSize);
                result = !potentialGroup.contains(Condition.OPERATIONAL);
                if (groupSize < springs.size()) {
                    result = result && springs.get(groupSize) != Condition.DAMAGED;
                }
            }
        }
        return result;
    }

    /**
     * @return condition record without taking the first spring into consideration
     */
    private ConditionRecord dropFirstSpring() {
        if (springs.isEmpty()) {
            throw new NoSuchElementException("No springs available.");
        }
        if (springs().getFirst() == Condition.DAMAGED) {
            throw new IllegalStateException("Unable to drop damaged spring without matching it to a group. " + this);
        }
        return new ConditionRecord(springs.subList(1, springs.size()), contiguousGroupSizes);
    }
    
    @Override
    public String toString() {
        var springsString = springs.stream()
                .map(Object::toString)
                .collect(Collectors.joining());
        var sizesString = contiguousGroupSizes.stream()
                .map(Object::toString)
                .collect(Collectors.joining(","));
        return springsString + " "  + sizesString;
    }
}
