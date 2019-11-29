package nl.mvdr.adventofcode.adventofcode2016.day10;

import java.util.HashSet;
import java.util.Set;

/**
 * Entity which is capable of holding microchips.
 *
 * @author Martijn van de Rijdt
 */
abstract class MicrochipHolder {
    /** Identification number. */
    private final int number;
    
    /** Values of the microchips currently held. */
    private final Set<Integer> microchips;
    
    /**
     * Constructor.
     * 
     * @param number identification number
     */
    MicrochipHolder(int number) {
        this.number = number;
        this.microchips = new HashSet<>();
    }
    
    /** @return whether this microchip holder can perform an action */
    abstract boolean canAct();
    
    Set<Integer> getMicrochips() {
        return microchips;
    }
    
    /** @return name of this microchip holder */
    String getName() {
        return getClass().getSimpleName().toLowerCase() + " " + number;
    }
    
    @Override
    public String toString() {
        return getName();
    }
}
