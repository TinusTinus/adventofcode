package nl.mvdr.adventofcode.adventofcode2022.day04;

/**
 * A cleaning assignment.
 * 
 * Both bounds are inclusive.
 *
 * @author Martijn van de Rijdt
 */
record Assignment(int lowerBound, int upperBound) {
    
    /**
     * Parses puzzle input.
     * 
     * @param text textual representation of an assignment, for example: "23-38"
     * @return the assignment
     */
    static Assignment parse(String text) {
        var parts = text.split("-");
        if (parts.length != 2) {
            throw new IllegalArgumentException("Invalid range: " + text);
        }
        var lowerBound = Integer.parseInt(parts[0]);
        var upperBound = Integer.parseInt(parts[1]);
        return new Assignment(lowerBound, upperBound);
    }
    
    /**
     * Determines whether this assignment fully contains the given other assignment.
     * 
     * @param other other assignment
     * @return whether this assignment fully contains the given other assignment
     */
    boolean fullyContains(Assignment other) {
        return this.lowerBound <= other.lowerBound && other.upperBound <= this.upperBound;
    }
    
    /**
     * Determines whether this assignment overlaps with the given other assignment.
     * 
     * @param other other assignment
     * @return whether this assignment fully contains the given other assignment
     */
    boolean overlaps(Assignment other) {
        return this.lowerBound <= other.upperBound && other.lowerBound <= this.upperBound;
    }
}
