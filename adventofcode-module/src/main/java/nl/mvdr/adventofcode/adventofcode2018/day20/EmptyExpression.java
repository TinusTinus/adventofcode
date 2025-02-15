package nl.mvdr.adventofcode.adventofcode2018.day20;

import java.util.List;

/**
 * Empty expression.
 *
 * @author Martijn van de Rijdt
 */
class EmptyExpression extends Concatenation {
    
    private static final EmptyExpression INSTANCE = new EmptyExpression();

    /** @return singleton instance */
    static EmptyExpression getInstance() {
        return INSTANCE;
    }

    /** Private constructor to prevent singleton instantiation. */
    private EmptyExpression() {
        // Implemented as a concatenation of 0 elements.
        super(List.of());
    }
}
