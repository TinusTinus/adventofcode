package nl.mvdr.adventofcode.adventofcode2022.day21;

import java.util.function.IntBinaryOperator;
import java.util.stream.Stream;

/**
 * A mathematical operator, used by a monkey to calculate its value.
 *
 * @author Martijn van de Rijdt
 */
enum Operator {
    /** Addition. */
    PLUS('+', (i, j) -> Math.addExact(i, j)),
    /** Subtraction. */
    MINUS('-', (i, j) -> Math.addExact(i, -j)),
    /** Multiplication. */
    TIMES('*', (i, j) -> Math.multiplyExact(i, j)),
    /** Division. */
    DIVIDE('/', (i, j) -> Math.divideExact(i, j));
    
    private final char representation;
    private final IntBinaryOperator binaryOperator;

    /**
     * Parses the given string representation of an operator.
     * 
     * @param string string represenation: +, -, * or /
     * @return operator
     */
    static Operator parse(String string) {
        return Stream.of(Operator.values())
                .filter(operator -> ("" + operator.representation).equals(string))
                .findFirst()
                .orElseThrow();
    }
    
    /**
     * Constructor.
     * 
     * @param representation character representation of this operator in the puzzle input
     * @param binaryOperator integer operator
     */
    Operator(char representation, IntBinaryOperator binaryOperator) {
        this.representation = representation;
        this.binaryOperator = binaryOperator;
    }
    
    /**
     * Applies this operator to the given numbers.
     * 
     * @param lhs left-hand side operand
     * @param rhs right-hand side operand
     * @return result
     */
    NumberValue apply(NumberValue lhs, NumberValue rhs) {
        return new NumberValue(binaryOperator.applyAsInt(lhs.number(), rhs.number()));
    }
}
