package nl.mvdr.adventofcode.adventofcode2017.day08;

import java.util.function.BiPredicate;
import java.util.stream.Stream;

/**
 * The operator in an instruction's condition.
 *
 * @author Martijn van de Rijdt
 */
enum ConditionOperator implements BiPredicate<Integer, Integer> {
    /** Less than (&lt;). */
    LESS_THAN("<", (i, j) -> i.intValue() < j.intValue()),
    /** At most, also know as "less than or equal to" (&le;).*/
    AT_MOST("<=", (i, j) -> i.intValue() <= j.intValue()),
    /** Equals (=). */
    EQUAL("==", (i, j) -> i.equals(j)),
    /** At least, also known as greater than or equal to (&ge;). */
    AT_LEAST(">=", (i, j) -> i.intValue() >= j.intValue()),
    /** Greater than (&gt;). */
    GREATER_THAN(">", (i, j) -> i.intValue() > j.intValue()),
    /** Does not equal (&ne;). */
    NOT_EQUAL("!=", (i, j) -> !i.equals(j));
    
    private final String stringRepresentation;
    
    private final BiPredicate<Integer, Integer> predicate;

    /**
     * Parses the given string into an operator value.
     * 
     * @param string string representation of an operator
     * @return operator
     */
    static ConditionOperator parse(String string) {
        return Stream.of(ConditionOperator.values())
                .filter(value -> value.toString().equals(string))
                .findAny()
                .get();
    }
    
    /**
     * Constructor.
     * 
     * @param stringRepresentation string representation of this operator
     * @param predicate predicate for this operator
     */
    ConditionOperator(String stringRepresentation, BiPredicate<Integer, Integer> predicate) {
        this.stringRepresentation = stringRepresentation;
        this.predicate = predicate;
    }
    
    @Override
    public boolean test(Integer i, Integer j) {
        return predicate.test(i, j);
    }
    
    @Override
    public String toString() {
        return stringRepresentation;
    }
}
