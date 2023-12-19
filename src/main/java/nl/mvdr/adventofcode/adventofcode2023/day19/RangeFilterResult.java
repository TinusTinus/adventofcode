package nl.mvdr.adventofcode.adventofcode2023.day19;

/**
 * The result of applying a rule to a range.
 *
 * @param <R> specific range type
 * @param applies the part of the range which applies
 * @param doesNotApply the part of the range which does not apply
 * @author Martijn van de Rijdt
 */
record RangeFilterResult<R>(R applies, R doesNotApply) {
    
}
