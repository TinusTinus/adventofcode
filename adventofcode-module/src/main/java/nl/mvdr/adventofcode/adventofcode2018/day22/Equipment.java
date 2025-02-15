package nl.mvdr.adventofcode.adventofcode2018.day22;

/**
 * Possible equipment values.
 * 
 * @author Martijn van de Rijdt
 */
enum Equipment {
    /** The torch is equipped (and the climbing gear is not). */
    TORCH,
    
    /** The climbing gear is equipped (and the torch is not). */
    CLIMBING_GEAR,
    
    /** Neither the torch nor the climbing gear is equipped. */
    NEITHER;
}
