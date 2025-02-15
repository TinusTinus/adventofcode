package nl.mvdr.adventofcode.adventofcode2022.day11;

import java.math.BigInteger;

/**
 * Representation of an item which has been stolen by a monkey.
 *
 * @author Martijn van de Rijdt
 */
record Item(BigInteger worryLevel) {
    
    @Override
    public String toString() {
        return "" + worryLevel;
    }
}
