package nl.mvdr.adventofcode.adventofcode2017.day08;

import java.util.function.IntBinaryOperator;
import java.util.stream.Stream;

/**
 * The operator part of an instruction.
 *
 * @author Martijn van de Rijdt
 */
enum Operator implements IntBinaryOperator {
    /** Increases the register value. */
    INCREASE("inc", (i, j) -> i + j),
    /** Decreases the register value. */
    DECREASE("dec", (i, j) -> i - j);
    
    private final String stringRepresentation;
    
    private final IntBinaryOperator intBinaryOperator;

    /**
     * Parses the given string into an operator value.
     * 
     * @param string string representation of an operator
     * @return operator
     */
    static Operator parse(String string) {
        return Stream.of(Operator.values())
                .filter(value -> value.toString().equals(string))
                .findAny()
                .get();
    }
    
    /**
     * Constructor.
     * 
     * @param stringRepresentation string representation of this operator
     * @param intBinaryOperator operator function
     */
    Operator(String stringRepresentation, IntBinaryOperator intBinaryOperator) {
        this.stringRepresentation = stringRepresentation;
        this.intBinaryOperator = intBinaryOperator;
    }
    
    @Override
    public int applyAsInt(int left, int right) {
        return intBinaryOperator.applyAsInt(left, right);
    }
    
    @Override
    public String toString() {
        return stringRepresentation;
    }
}
