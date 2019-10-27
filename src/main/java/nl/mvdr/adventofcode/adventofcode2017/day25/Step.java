package nl.mvdr.adventofcode.adventofcode2017.day25;

import java.util.HashSet;
import java.util.Set;

/**
 * Specification of a single step in a Turing machine.
 *
 * @author Martijn van de Rijdt
 */
class Step {
    /** Value to write: false for 0, true for 1. */
    private final boolean value;
    /** The direction of the next slot: 1 for right, -1 for left. */
    private final int direction;
    /** Name of the next state. */
    private final String nextState;
    
    /**
     * Parses text lines representing this step.
     * 
     * @param valueLine text representation of the value, such as: "    - Write the value 1."
     * @param directionLine text representation of the direction, such as: "    - Move one slot to the right."
     * @param stateLine text representation of the next state, such as: "    - Continue with state B."
     * @return step
     */
    static Step parse(String valueLine, String directionLine, String stateLine) {
        boolean value = valueLine.contains("1");
        int direction;
        if (directionLine.contains("right")) {
            direction = 1;
        } else {
            direction = -1;
        }
        String state = stateLine.substring("    - Continue with state ".length(), stateLine.length() - 1);
        return new Step(value, direction, state);
    }
    
    /**
     * Constructor.
     * 
     * @param value false for 0, true for 1
     * @param direction 1 for right, -1 for left
     * @param nextState name of the next state
     */
    private Step(boolean value, int direction, String nextState) {
        super();
        this.value = value;
        this.direction = direction;
        this.nextState = nextState;
    }
    
    /**
     * Executes this step.
     * 
     * @param machine initial Turing machine
     * @return new Turing machine, updated after executing this step
     */
    TuringMachine execute(TuringMachine machine) {
        Set<Integer> tape = new HashSet<>(machine.getTape());
        if (value) {
            tape.add(Integer.valueOf(machine.getCursor()));
        } else {
            tape.remove(Integer.valueOf(machine.getCursor()));
        }
        
        int cursor = machine.getCursor() + direction;
        
        return new TuringMachine(machine.getDefinition(), tape, cursor, nextState);
    }
}
