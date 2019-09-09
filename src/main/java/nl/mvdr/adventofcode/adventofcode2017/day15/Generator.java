package nl.mvdr.adventofcode.adventofcode2017.day15;

/**
 * A generator.
 * 
 * @author Martijn van de Rijdt
 */
class Generator {
    
    private static final int DIVISOR = 2_147_483_647;
    
    /** This generator's factor. */
    private final int factor;
    /** Previously generated value (initially: the start value). */
    private int previousValue;
    
    /**
     * Constructor.
     * 
     * @param factor this generator's factor
     * @param startValue starting value for this generator (from the puzzle input)
     */
    Generator(int factor, int startValue) {
        super();
        this.factor = factor;
        this.previousValue = startValue;
    }
    
    /**
     * Generates the next value.
     * 
     * Note that this updates the state of this generator.
     * 
     * @return next value
     */
    int nextValue() {
        int result = previousValue * factor % DIVISOR;
        
        previousValue = result;
        
        return result;
    }
}
