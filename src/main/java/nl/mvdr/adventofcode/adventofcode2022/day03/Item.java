package nl.mvdr.adventofcode.adventofcode2022.day03;

import java.util.HashSet;
import java.util.Set;

/**
 * Representation of an item.
 *
 * @author Martijn van de Rijdt
 */
record Item(char representation) {
    
    /**
     * Determins the (unique) common item between the given sets of items.
     * 
     * @param itemSets item containers (compartments or rucksacks)
     * @return the (unique) common item between the given sets
     */
    static Item commonItem(Set<Set<Item>> itemSets) {
        var iterator = itemSets.iterator();
        
        Set<Item> resultSet = new HashSet<>(iterator.next());
        
        while(iterator.hasNext()) {
            resultSet.retainAll(iterator.next());
        }
        
        if (resultSet.size() != 1) {
            throw new IllegalStateException("Expected exactly one common item, but found: " + resultSet);
        }
        
        return resultSet.iterator().next();
    }
    
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
