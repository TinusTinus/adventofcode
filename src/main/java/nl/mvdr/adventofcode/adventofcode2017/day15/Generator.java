package nl.mvdr.adventofcode.adventofcode2017.day15;

/**
 * A generator.
 * 
 * @author Martijn van de Rijdt
 */
class Generator {
    
    private static final long FACTOR_A = 16_807L;
    private static final long FACTOR_B = 48_271L;
    
    private static final long CRITERIUM_A = 4L;
    private static final long CRITERIUM_B = 8L;
    
    private static final long DIVISOR = 2_147_483_647L;
    
    /** This generator's factor. */
    private final long factor;
    /** This generator will only return values that are divisible by this number. */
    private final long criterium;
    /** Previously generated value (initially: the start value). */
    private long previousValue;

    /**
     * Creates Generator A.
     * 
     * @param startValue start value
     * @param picky whether the generator is picky
     * @return Generator A
     */
    static Generator createGeneratorA(long startValue, boolean picky) {
        Generator result;
        if (picky) {
            result = new Generator(FACTOR_A, CRITERIUM_A, startValue);
        } else {
            result = new Generator(FACTOR_A, startValue);
        }
        return result;
    }
    
    /**
     * Creates Generator B.
     * 
     * @param startValue start value
     * @param picky whether the generator is picky
     * @return Generator B
     */
    static Generator createGeneratorB(long startValue, boolean picky) {
        Generator result;
        if (picky) {
            result = new Generator(FACTOR_B, CRITERIUM_B, startValue);
        } else {
            result = new Generator(FACTOR_B, startValue);
        }
        return result;
    }
    
    /**
     * Constructor for a generator without additional criteria.
     * 
     * @param factor this generator's factor
     * @param startValue starting value for this generator (from the puzzle input)
     */
    private Generator(long factor, long startValue) {
        this(factor, 1L, startValue);
    }
    
    /**
     * Constructor.
     * 
     * @param factor this generator's factor
     * @param criterium this generator will only return values that are divisible by this number
     * @param startValue starting value for this generator (from the puzzle input)
     */
    private Generator(long factor, long criterium, long startValue) {
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
