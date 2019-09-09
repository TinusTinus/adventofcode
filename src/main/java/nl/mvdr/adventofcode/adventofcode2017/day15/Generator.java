package nl.mvdr.adventofcode.adventofcode2017.day15;

/**
 * A generator.
 * 
 * @author Martijn van de Rijdt
 */
class Generator {
    
    private static final long DIVISOR = 2_147_483_647L;
    
    /** This generator's factor. */
    private final long factor;
    /** This generator will only return values that are divisible by this number. */
    private final long criterium;
    /** Previously generated value (initially: the start value). */
    private long previousValue;

    /**
     * Constructor for a generator without additional criteria.
     * 
     * @param factor this generator's factor
     * @param startValue starting value for this generator (from the puzzle input)
     */
    Generator(long factor, long startValue) {
        this(factor, 1L, startValue);
    }
    
    /**
     * Constructor.
     * 
     * @param factor this generator's factor
     * @param criterium this generator will only return values that are divisible by this number
     * @param startValue starting value for this generator (from the puzzle input)
     */
    Generator(long factor, long criterium, long startValue) {
        super();
        this.factor = factor;
        this.criterium = criterium;
        this.previousValue = startValue;
    }
    
    /**
     * Generates the next value.
     * 
     * Note that this updates the state of this generator.
     * 
     * @return next value
     */
    long nextValue() {
        long result = previousValue * factor % DIVISOR;
        while (result % criterium != 0) {
            result = previousValue * factor % DIVISOR;
        }
        
        previousValue = result;
        
        return result;
    }
}
