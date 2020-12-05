package nl.mvdr.adventofcode.adventofcode2020.day01;

/**
 * A pair of integers.
 *
 * @param lhs the first integer (left-hand side)
 * @param rhs the second integer (right-hand side)
 * 
 * @author Martijn van de Rijdt
 */
record Pair(int lhs, int rhs) {
    /** @return the result of adding the two integers together */
    int sum() {
        return lhs + rhs;
    }
    
    /** @return the result of multiplying the two integers with each other */
    int product() {
        return lhs * rhs;
    }
}
