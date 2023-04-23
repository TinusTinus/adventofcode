package nl.mvdr.adventofcode.adventofcode2022.day11;

/**
 * Representation of an item which has been stolen by a monkey.
 *
 * @author Martijn van de Rijdt
 */
record Item(int worryLevel) {
    
    @Override
    public String toString() {
        return "" + worryLevel;
    }
}
