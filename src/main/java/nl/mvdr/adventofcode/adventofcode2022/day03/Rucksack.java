package nl.mvdr.adventofcode.adventofcode2022.day03;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Representation of a rucksack.
 * 
 * @author Martijn van de Rijdt
 */
record Rucksack(Set<Item> leftCompartment, Set<Item> rightCompartment) {
    
    /**
     * Parses a line from the puzzle input.
     * 
     * @param text textual representation of a rucksack
     * @return rucksack
     */
    static Rucksack parse(String text) {
        if (text.length() % 2 != 0) {
            // Each compartment must have the same number of items, so the input's length must be even.
            throw new IllegalArgumentException("Uneven input");
        }
        var itemsPerCompartment = text.length() / 2;
        
        var leftCompartment = toCompartment(text.substring(0, itemsPerCompartment));
        var rightCompartment = toCompartment(text.substring(itemsPerCompartment));
        return new Rucksack(leftCompartment, rightCompartment);
    }
    
    /**
     * Parses a compartment.
     * 
     * @param text textual representation of a compartment
     * @return items in the compartment
     */
    private static Set<Item> toCompartment(String text) {
        return text.chars()
                .mapToObj(character -> Character.valueOf((char) character))
                .map(Item::new)
                .collect(Collectors.toSet());
    }
    
    /**
     * @return the (unique) common item between both compartments
     */
    Item commonItem() {
        Set<Item> resultSet = new HashSet<>(leftCompartment);
        resultSet.retainAll(rightCompartment);
        
        if (resultSet.size() != 1) {
            throw new IllegalStateException("Expected exactly one common item, but found: " + resultSet);
        }
        
        return resultSet.iterator().next();
    }
}
