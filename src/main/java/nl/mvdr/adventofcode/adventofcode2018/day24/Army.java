package nl.mvdr.adventofcode.adventofcode2018.day24;

/**
 * Representation of an army.
 *
 * @author Martijn van de Rijdt
 */
enum Army {
    IMMUNE_SYSTEM("Immune System"), 
    INFECTION("Infection");
    
    private final String representation;
    
    Army(String representation) {
        this.representation = representation;
    }
    
    @Override
    public String toString() {
        return representation;
    }
}
