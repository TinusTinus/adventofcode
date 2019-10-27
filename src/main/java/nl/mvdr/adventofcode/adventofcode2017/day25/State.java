package nl.mvdr.adventofcode.adventofcode2017.day25;

import java.util.List;

/**
 * Turing machine state.
 *
 * @author Martijn van de Rijdt
 */
class State {
    
    /** Step to take in case of a 0 value. */
    private final Step step0;
    /** Step to take in case of a 1 value. */
    private final Step step1;
    
    /**
     * Parses lines of text representing a state (exlcluding the line containing the name of the state).
     * 
     * For example:
     * <pre>
     *   If the current value is 0:
     *     - Write the value 1.
     *     - Move one slot to the right.
     *     - Continue with state B.
     *   If the current value is 1:
     *     - Write the value 0.
     *     - Move one slot to the left.
     *     - Continue with state B.
     * </pre>
     * 
     * @param lines
     * @return
     */
    static State parse(List<String> lines) {
        if (lines.size() != 8
                || !"  If the current value is 0:".equals(lines.get(0))
                || !"  If the current value is 1:".equals(lines.get(4))) {
            throw new IllegalArgumentException("Unable to parse: " + lines);
        }
        Step step0 = Step.parse(lines.get(1), lines.get(2), lines.get(3));
        Step step1 = Step.parse(lines.get(5), lines.get(6), lines.get(7));
        return new State(step0, step1);
    }
    
    /**
     * Constructor.
     * 
     * @param step0 step to take in case of a 0 value
     * @param step1 step to take in case of a 1 value
     */
    private State(Step step0, Step step1) {
        super();
        this.step0 = step0;
        this.step1 = step1;
    }
}
