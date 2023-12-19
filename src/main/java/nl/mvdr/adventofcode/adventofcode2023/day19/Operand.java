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
            RangeFilterResult<ValueRange> result;
            if (value <= range.startInclusive()) {
                // All values in the range are at least the given value.
                result = new RangeFilterResult<>(ValueRange.EMPTY_RANGE, range);
            } else if (range.endExclusive() <= value) {
                // All values in the range are less than the given value.
                result = new RangeFilterResult<>(range, ValueRange.EMPTY_RANGE);
            } else {
                // The given value is somewhere within the range.
                var applies = new ValueRange(range.startInclusive(), value);
                var doesNotApply = new ValueRange(value, range.endExclusive());
                result = new RangeFilterResult<>(applies, doesNotApply);
            }
            return result;
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
