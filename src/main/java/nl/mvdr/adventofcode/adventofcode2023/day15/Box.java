package nl.mvdr.adventofcode.adventofcode2023.day15;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

/**
 * A box of lenses.
 * 
 * Note that the list of lenses is <em>mutable</em>.
 * This means that the {@link #equals(Object)} and {@link #hashCode()} methods
 * of this class should not be trusted to return consistent results.
 *
 * @author Martijn van de Rijdt
 */
record Box(List<Lens> lenses) {
    
    /** Constructor. */
    Box() {
        this(new ArrayList<>());
    }
    
    /**
     * Removes the lens with the given label if present.
     * 
     * @param label lens label
     */
    void remove(String label) {
        lenses.removeIf(lens -> label.equals(lens.label()));
    }
    
    void add(Lens lens) {
        IntStream.range(0, lenses.size())
                .filter(index -> lens.label().equals(lenses.get(index).label()))
                .findFirst()
                .ifPresentOrElse(
                        index -> lenses.set(index, lens), // replace the lens
                        () -> lenses.add(lens)); // add the lens
    }
    
    /**
     * @return total focusing power of the lenses in this box
     */
    int focusingPower() {
        return IntStream.range(0, lenses.size())
                .map(i -> lenses.get(i).focusingPower(i + 1))
                .sum();
    }
}
