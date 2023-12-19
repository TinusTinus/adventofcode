package nl.mvdr.adventofcode.adventofcode2023.day19;

import java.util.stream.Stream;

/**
 * An operand for use in a rule, when comparing a property value.
 *
 * @author Martijn van de Rijdt
 */
enum Operand {
    LESS_THAN("<") {
        @Override
        boolean apply(int lhs, int rhs) {
            return lhs < rhs;
        }

        @Override
        RangeFilterResult<ValueRange> filter(ValueRange range, int value) {
            // TODO implement for real
            return new RangeFilterResult<>(range, ValueRange.EMPTY_RANGE);
        }
    },
    GREATER_THAN(">") {
        @Override
        boolean apply(int lhs, int rhs) {
            return lhs > rhs;
        }

        @Override
        RangeFilterResult<ValueRange> filter(ValueRange range, int value) {
            return LESS_THAN.filter(range, value).swap();
        }
    };
    
    private final String representation;
    
    /**
     * Parses an operand.
     * 
     * @param representation string representation of the operand
     * @return operand
     */
    static Operand parse(String representation) {
        return Stream.of(values())
                .filter(value -> value.representation.equals(representation))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Not a valid operand: " + representation));
    }
    
    /**
     * Constructor.
     * @param represntation string representation of the operand
     */
    Operand(String representation) {
        this.representation = representation;
    }
    
    /**
     * Applies this operand.
     * 
     * @param lhs left-hand side
     * @param rhs right-hand side
     * @return result
     */
    abstract boolean apply(int lhs, int rhs);
    
    /**
     * Splits the given range into two.
     * 
     * The filter result returned by this method contains two ranges:
     * one represents the values which satisfy the condition,
     * the other represents the values which do not.
     * The two ranges together make up the given range.
     * 
     * @param range range of values
     * @param value the value to compare to
     * @return split range
     */
    abstract RangeFilterResult<ValueRange> filter(ValueRange range, int value);
}
