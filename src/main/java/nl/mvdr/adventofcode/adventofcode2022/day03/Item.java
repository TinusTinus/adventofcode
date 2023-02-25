package nl.mvdr.adventofcode.adventofcode2022.day03;

/**
 * Representation of an item.
 *
 * @author Martijn van de Rijdt
 */
record Item(char representation) {
    
    /**
     * @return this item's priority
     */
    int priority() {
        int offset;
        if (Character.isLowerCase(representation)) {
            offset = 'a';
        } else if (Character.isUpperCase(representation)) {
            offset = 'A' - 26;
        } else {
            throw new IllegalStateException("Unable to determine priority for " + this);
        }
        return 1 + representation - offset;
    }
}
