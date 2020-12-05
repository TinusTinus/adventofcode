package nl.mvdr.adventofcode.adventofcode2020.day01;

/**
 * A collection of three integers.
 *
 * @param first the first integer
 * @param second the second integer
 * @param third the third integer
 * 
 * @author Martijn van de Rijdt
 */
record Trio(int first, int second, int third) {
    /** @return the result of adding the integers together */
    int sum() {
        return first + second + third;
    }
    
    /** @return the result of multiplying the integers with each other */
    int product() {
        return first * second * third;
    }
}
