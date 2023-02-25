package nl.mvdr.adventofcode.adventofcode2022.day04;

/**
 * A pair of assignments.
 *
 * @author Martijn van de Rijdt
 */
record Pair(Assignment first, Assignment second) {
    
    /**
     * Parses puzzle input.
     * 
     * @param text textual representation of a pair of assignments, for example: "23-38,25-41"
     * @return the assignment
     */
    static Pair parse(String text) {
        var parts = text.split(",");
        if (parts.length != 2) {
            throw new IllegalArgumentException("Invalid pair: " + text);
        }
        var firstAssignment = Assignment.parse(parts[0]);
        var secondAssignment = Assignment.parse(parts[1]);
        return new Pair(firstAssignment, secondAssignment);
    }
    
    /**
     * @return whether one of the given assignments fully contains the other
     */
    boolean fullyContains() {
        return first.fullyContains(second) || second.fullyContains(first);
    }
    
    /**
     * @return whether the assignments overlap
     */
    boolean overlaps() {
        return first.overlaps(second);
    }
}
