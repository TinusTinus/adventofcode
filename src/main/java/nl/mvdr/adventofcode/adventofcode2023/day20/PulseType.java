package nl.mvdr.adventofcode.adventofcode2023.day20;

/**
 * A pulse.
 *
 * @author Martijn van de Rijdt
 */
enum PulseType {
    LOW,
    HIGH;
    
    @Override
    public String toString() {
        return super.toString().toLowerCase();
    }
}
