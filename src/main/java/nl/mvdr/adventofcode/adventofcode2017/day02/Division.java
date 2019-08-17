package nl.mvdr.adventofcode.adventofcode2017.day02;

import java.util.function.IntBinaryOperator;

/**
 * Representation of the division of two integers.
 *
 * @author Martijn van de Rijdt
 */
class Division implements IntBinaryOperator {
    
    private final int dividend;
    private final int divisor;
    
    Division(int dividend, int divisor) {
        super();
        this.dividend = dividend;
        this.divisor = divisor;
    }
    
    /** @return whether this is an even division */
    boolean isEven() {
        return dividend % divisor == 0;
    }
    
    @Override
    public int applyAsInt(int left, int right) {
        return dividend / divisor;
    }
}
