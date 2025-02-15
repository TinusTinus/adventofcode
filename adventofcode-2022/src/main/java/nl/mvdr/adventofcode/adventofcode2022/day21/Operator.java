package nl.mvdr.adventofcode.adventofcode2022.day21;

import java.util.function.LongBinaryOperator;
import java.util.stream.Stream;

/**
 * A mathematical operator, used by a monkey to calculate its value.
 *
 * @author Martijn van de Rijdt
 */
enum Operator {
    /** Addition. */
    PLUS('+', (i, j) -> Math.addExact(i, j), (rhs, target) -> Math.addExact(target, -rhs), (lhs, target) -> Math.addExact(target, -lhs)),
    /** Subtraction. */
    MINUS('-', (i, j) -> Math.addExact(i, -j), (rhs, target) -> Math.addExact(target, rhs), (lhs, target) -> Math.addExact(lhs, -target)),
    /** Multiplication. */
    TIMES('*', (i, j) -> Math.multiplyExact(i, j), (rhs, target) -> Math.divideExact(target, rhs), (lhs, target) -> Math.divideExact(target, lhs)),
    /** Division. */
    DIVIDE('/', (i, j) -> Math.divideExact(i, j), (rhs, target) -> Math.multiplyExact(target, rhs), (lhs, target) -> Math.divideExact(lhs, target)),
    /** The special MATCH operator, used by the root monkey in part 2 of the puzzle. */
    MATCH('=', (i, j) -> 0L, (rhs, target) -> rhs, (lhs, target) -> lhs);
    
    private final char representation;
    private final LongBinaryOperator binaryOperator;
    private final LongBinaryOperator findLhsOperator;
    private final LongBinaryOperator findRhsOperator;

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
     * @param binaryOperator operator
     * @param findLhsOperator inverse operator
     * @param findRhsOperator inverse operator
     */
    Operator(char representation, LongBinaryOperator binaryOperator, LongBinaryOperator findLhsOperator, LongBinaryOperator findRhsOperator) {
        this.representation = representation;
        this.binaryOperator = binaryOperator;
        this.findLhsOperator = findLhsOperator;
        this.findRhsOperator = findRhsOperator;
    }
    
    /**
     * Applies this operator to the given numbers.
     * 
     * @param lhs left-hand side operand
     * @param rhs right-hand side operand
     * @return result
     */
    NumberValue apply(NumberValue lhs, NumberValue rhs) {
        return new NumberValue(binaryOperator.applyAsLong(lhs.number(), rhs.number()));
    }
    
    /**
     * Finds the left-hand side value of this operator, given the right-hand side value and the expression value.
     * 
     * @param rhs right-hand side operand
     * @param target expression value
     * @return result
     */
    long findLhs(NumberValue rhs, long target) {
        return findLhsOperator.applyAsLong(rhs.number(), target);
    }
    
    /**
     * Finds the right-hand side value of this operator, given the left-hand side value and the expression value.
     * 
     * @param lhs left-hand side operand
     * @param target expression value
     * @return result
     */
    long findRhs(NumberValue lhs, long target) {
        return findRhsOperator.applyAsLong(lhs.number(), target);
    }
}
